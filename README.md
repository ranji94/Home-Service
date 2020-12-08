## Home Service

Latest stable release: 1.0.0-SNAPSHOT

Home service, current version gives ability to control home costs for energy and water within specified billing cycle, monitor media usage and automates e-mail correspondention with landlord.

# Warning:
It is highly recommended to use deploy this service only on servers over local network, next releases will be focused on security.

# 1. Deployment over Linux server (Tested on Raspberry Pi 4b)
* Install PostgreSQL on server, configure users and create empty database for example "homeservicedb"
* Build Docker image or JAR package using gradle tools
* Run Docker image or create scripts to run JAR as Linux service then enable it 
# 2. On client machine
* Go to http://host_ip_address:port/swagger-ui/index.html (default port is 8080)
* Open Maintenance/setDbCredentials
* Fill all fields to push database configuration to Home Service:
- database: Enter name of database created on your PostgreSQL, in this example: "homeservicedb"
- password: Enter password of user which will be connecting to database
- server: Enter IP address of server machine for example "192.168.0.52"
- user: Name of user which will be logged in to database, default for raspberry pi 4b is: "pi"
* After successful pushing database credentials, restart running home service "sudo systemctl restart home.service"
* Now service is configured and ready to work with HomeUI app
