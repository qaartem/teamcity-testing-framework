cd ..

teamcity_tests_directory=$(pwd)
workdir="teamcity_tests_infrastructure"
teamcity_server_workdir="teamcity_server"
teamcity_agent_workdir="teamcity_agent"
selenoid_workdir="selenoid"
teamcity_server_container_name="teamcity_server_instance"
teamcity_agent_container_name="teamcity_agent_instance"
selenoid_container_name="selenoid_instance"
selenoid_ui_container_name="selenoid_ui_instance"

workdir_names=($teamcity_server_workdir $teamcity_agent_workdir $selenoid_workdir)
container_names=($teamcity_server_container_name $teamcity_agent_container_name $selenoid_container_name $selenoid_ui_container_name)

################################
echo "Request IP"

ip=$(ipconfig | grep -A2 "IPv4 Address. . . . . . . . . . . : [0-9]*" | awk 'NR==5 {print $NF}')
echo "Current IP: $ip"

################################
echo "Delete previous run data"

rm -rf $workdir
mkdir $workdir
cd $workdir

for dir in "${workdir_names[@]}"; do
  mkdir $dir
done

for container in "${container_names[@]}"; do
  docker stop $container
  docker rm $container
done

################################

echo "Start teamcity server"

cd $teamcity_server_workdir

current=$(powershell.exe '$PWD -replace "\\", "/" -replace "C", "c"')

docker run -d --name $teamcity_server_container_name\
    -v $current/logs:/opt/teamcity/logs\
    -p 8111:8111\
    jetbrains/teamcity-server

echo "Teamcity Server is running..."
################################
echo "Start teamcity agent"

cd ..

cd $teamcity_agent_workdir

docker run -d --name $teamcity_agent_container_name \
    -e SERVER_URL="http://$ip:8111" \
    -v $current/conf:/data/teamcity_agent/conf \
    jetbrains/teamcity-agent

echo "Teamcity Agent is running..."

################################
echo "Start selenoid"

cd .. && cd $selenoid_workdir

mkdir config

cp $teamcity_tests_directory/infra/browsers.json config/

current=$(powershell.exe '$PWD -replace "\\", "/" -replace "C", "c"')

docker run -d                                   \
            --name $selenoid_container_name                                 \
            -p 4444:4444                                    \
            -v //var/run/docker.sock:/var/run/docker.sock    \
            -v $current/config/:/etc/selenoid/:ro              \
    aerokube/selenoid:latest-release

image_names=($(awk -F'"' '/"image": "/{print $4}' "$(pwd)/config/browsers.json"))

echo "Pull all browser images: $image_names"

for image in "${image_names[@]}"; do
  docker pull $image
done

cd ..
image_names=($(awk -F'"' '/"image": "/{print $4}' "c:/Users/artem.mindsadyrov/AquaProjects/teamcity-API-test/teamcity_tests_infrastructure/selenoid/config/browsers.json
"))

echo "Pull all browser images: $image_names"

for image in "${image_names[@]}"; do
  docker pull $image
done

################################
echo "Start selenoid-ui"

docker run -d --name $selenoid_ui_container_name \
           -p 80:8080 \
           aerokube/selenoid-ui:latest-release \
           --selenoid-uri "http://$ip:4444"

################################
echo "Setup teamcity server"

cd "$teamcity_tests_directory"

echo "Current directory: $current"

mvn clean test -Dtest=SetupTest#startUpTest

################################
echo "Parse superuser token"
superuser_token=$(grep -o 'Super user authentication token: [0-9]*' $teamcity_tests_directory/$workdir/$teamcity_server_workdir/logs/teamcity-server.log | awk '{print $NF}')
echo "Super user token: $superuser_token"

echo "superUserToken=$superuser_token" >> $teamcity_tests_directory/src/main/resources/config.properties
cat $teamcity_tests_directory/src/main/resources/config.properties

################################
echo "Run system tests"

config=$teamcity_tests_directory/src/main/resources/config.properties

echo "host=$ip:8111" > $config
echo "superUserToken=$superuser_token" >> $config
echo "remote=http://$ip:4444/wd/hub" >> $config
echo "browser=firefox" >> $config
cat $config

echo "Current directory: $(pwd)"
echo "Run API tests"
mvn test -DsuiteXmlFile=testng-suites/api-suite.xml

echo "Run UI tests"
mvn test -DsuiteXmlFile=testng-suites/ui-suite.xml

read -p "Press any key to continue"
