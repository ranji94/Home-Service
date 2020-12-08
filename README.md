## Home Service

Latest stable release: 1.0.0-SNAPSHOT

Home service, current version gives ability to control home costs for energy and water within specified billing cycle, monitor media usage and automates e-mail correspondention with landlord.

# Warning:
It is highly recommended to use deploy this service only on servers over local network, next releases will be focused on security.

# 1. Deployment over Linux Server (Tested on Raspberry Pi 4b)
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
# 3. Now perform on your Linux Server
* After successful pushing database credentials, restart running home service "sudo systemctl restart home.service"
* Now service is configured and ready to work with HomeUI app

# (Optional) Manual e-mail configuration
This action can be completed using HomeUI app. But there is also alternative way to configure e-mail exists:
* Go to http://host_ip_address:port/swagger-ui/index.html (default port is 8080)
* Open Mail/Update
* Fill fields like as described below:
- mailpass: Your source e-mail account password
- signature: Signature added to every sent message, it can be something like: "Kind Regards,{CR}John Smith"
- source: Source e-mail addres in format like: "john.smith@server.com"
- subject: Subject template for every e-mail message
- target: Target e-mail addres in format by analogy like source
- template: Body of e-mail address with limit to 4096 characters.

* template, subject, signature fields supports automatic inserting of collected values and formatting templates by using program variables:
{CR} - carriage return, this character enters new line
{month} - inserts current month, for example "December"
{year} - inserts current year, for example "2020"
{energy} - last collected energy meter value, returns for example "43.843 kWh"
{kitchen_cold} - refers to water meter value, first element of variable is room name, and second one is name of water meter (hot/cold). Returns for example: "13.435 m3". Other available variables like this: {kitchen_hot}, {bathroom_cold}, {bathroom_hot}
{signature} - Variable dedicated only for template body, points where signature should be placed in message body
