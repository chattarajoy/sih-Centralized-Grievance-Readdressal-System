# README

#### API Server running on : 192.168.117.60:8000

### Logging in

* User : url: /auth/user_login
parameters: email and password

* Admin : url  /auth/admin_login
parameters: email and password

#### Return Structure

Returns json {status, access_token, secret_key and (notice on error)}

### Registering a User

* User : url: /user/siginup
parameters: name, contact, email, password 

*Please input password twice on front-end and ensure both are same*
<br>*Contact refers to contact number or phone number*

* Admin user : url: /admin_user/signup
parameters: name, email, phone, access_level, municipality_id, password

*access_level and municipality_id will be handled by us in further iterations, please fill anything in it for now.*

#### Return Structure

Returns json {status and (notice on error)}

### Registering a complaint

*Comming soon*
