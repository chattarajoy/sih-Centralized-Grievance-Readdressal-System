# README

*API Server running on :  54.169.134.133*

## Authentication

* User : `url: /auth/user_login`
```
parameters: email, password

```

```
example call

192.168.117.60:8000/auth/user_login?email=xyz@xyz.com&password=tiru

```
#### Return Structure - User Login

```
Returns json 

 {status: "success" or "error", 
  access_token: "", 
  secret_key: "",
  user_name: "",
  email: "",
  contact: "",
  aadhaar_verified: true/false,
  phone_no_verified: true/false,
  error_message: "if there is an error"}

```

* Admin : url  `/auth/admin_login`

```
parameters: email, password

```

#### Return Structure - Admin Login

```
Returns json 

 {status: "success" or "error", 
  access_token: "", 
  secret_key: "",
  user_name: "",
  email: "",
  contact: "",
  role: "",
  error_message: "if there is an error"}

```

## User and Admin Users

### Sign-up

* User: `url: /user/signup`

```
parameters: name, contact, email, password

```

*Please input password twice on front-end and ensure both are same*
<br>*Contact refers to contact number or phone number*

* Admin user : `url: /admin_user/create`

```
parameters: name, email, phone, designation, municipal_id, department, password

```

*access_level and municipality_id will be handled by us in further iterations, please fill anything in it for now.*

#### Return Structure : Sign-up

```

Returns json 
 {status: "success" or "error",
  error_message: "if there is an error"}

```

### Update Password

* User: 'url: /user/update_password'

```
headers: access_token, secret_key
parameters: old_password, new_password
```

* Admin User: 'url: /admin_user/update_password'

```
headers: access_token, secret_key
parameters: email, password
```

#### Return Structure - Update Password

```

Returns json 
 {status: "success" or "error",
  error_message: "if there is an error"}

```

## Complaints

### New complaint

* `url: /complaint/create`
```
headers: access_token, secret_key
parameters: subject, sub_category, description, image, latitude, longitude, address, district, state, pincode
```

#### Return Structure - New Complaint

```

Returns json 
 {status: "success" or "error",
  error_message: "if there is an error",
  "complaint": {
    "id": 1,
    "subject": "New",
    sub_category: "",
    "description": "kaafi dikkat hai",
    "image": null,
    "latitude": null,
    "longitude": null,
    "city": "Ranchi",
    "state": "Jharkhand",
    "pincode": 835215,
    "created_at": "2017-03-25T14:52:17.834Z",
    "updated_at": "2017-03-25T14:52:17.834Z",
    "user_id": 1,
    "status": "new",
    "priority": "new"
  }
  }

```

### View complaints
* Get complaints for a user `url: /complaint/show_user_complaints`
```
headers: access_token, secret_key
```

#### Return Structure - complaints by user

```
Returns json: a list of complaints

sample return data

[
  {
    "id": 1,
    "subject": null,
    "sub_category": "",
    "description": null,
    "image": null,
    "latitude": null,
    "longitude": null,
    "city": null,
    "state": null,
    "pincode": null,
    "created_at": "2017-03-25T09:51:34.733Z",
    "updated_at": "2017-03-25T09:51:34.733Z",
    "user_id": 6,
    "status": "new",
    "priority": "new"
  },
  {
    "id": 2,
    "subject": "New",
    "sub_category": "",
    "description": "kaafi dikkat hai",
    "image": null,
    "latitude": null,
    "longitude": null,
    "city": "Ranchi",
    "state": "Jharkhand",
    "pincode": 835215,
    "created_at": "2017-03-25T10:59:01.214Z",
    "updated_at": "2017-03-25T10:59:01.214Z",
    "user_id": 6,
    "status": "new",
    "priority": "new"
  }
]
```

* Get complaint by complaint id : `url: /complaints/show_complaint_by_id`
```
headers: access_token, secret_key
parameters: id (complaint id)
```

#### Return Structure - complaint by id

```
Returns json with following fields

sample return data

{
  "id": 2,
  "subject": "New",
  "description": "kaafi dikkat hai",
  "image": null,
  "latitude": null,
  "longitude": null,
  "city": "Ranchi",
  "state": "Jharkhand",
  "pincode": 835215,
  "created_at": "2017-03-25T10:59:01.214Z",
  "updated_at": "2017-03-25T10:59:01.214Z",
  "user_id": 6,
  "status": "new",
  "priority": "new"
}
```
#### Get complaint updates

* URL: `/complaint/get_updates`
```
PARAMETERS: complaint_id
HEADERS: access_token, secrrt_key

RESPONSE

json: {status: "success",
       updates: updates}
       
Structure of updates

{"complaint_id":"",
 "assigned_to":"",
 "notes":"",
}

```
## Aadhar Verification

#### Requesting an OTP

* GET/POST `url: /aadhar_verification/verify_aadhar_data`

```
headers: access_token, secret_key
parameters: aadhar_number, contact
```
```
RESPONSE

json : {status: "error"/"success", 
        error_message: "data not found"/ message: "OTP sent"}
```

#### Verifying an OTP

* GET/POST `url: /aadhar_verification/verify_otp`
```
headers: access_token, secret_key
parameters: otp
```
*User gets 3 attempts to verify otp, OTP is deleted thereafter*4

```
RESPONSE

json : {status: "error"/"success", 
        error_message: "Invalid OTP"/ message: "OTP verified"}
```

# Admin Functions

### Fetch my complaints

* GET/POST : `url: /admin_user/fetch_complaints`

```
Headers: header tokens

RESPONSE

json: {new_complaint: new_complaint, 
       pending_complaint: pending_complaint, 
       completed_complaint: completed_complaint}
       
each of new_complaint, pending_complaint and completed_complaint has an architecture of 

{
  "admin_user_id":,
  "district_office_id":,
  "ward_office_id":,
  "status":,
  "created_at":,  
  "updated_at":,   
  "department":,
  "sub_category":,
  "complaint_id":,
}

```

### Fetch stats

* GET/POST : `url: /admin_user/fetch_statistics`

```
Headers: header tokens

RESPONSE 

json: {stats: [new_complaint, pending_complaint, completed_complaint] }
```
### transfer complaint

### mark completed
