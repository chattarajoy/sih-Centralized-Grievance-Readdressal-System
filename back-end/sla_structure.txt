u=Sla.create(category:"public health",time:72,subcategory:"birth/death certificate issues")
u=Sla.create(category:"public health",time:24,subcategory:"cleanliness of public toilets")
u=Sla.create(category:"public health",time:24,subcategory:"clearing of garbage bins")
u=Sla.create(category:"public health",time:6,subcategory:"death of stray animals")
u=Sla.create(category:"public health",time:24,subcategory:"dog menace")
u=Sla.create(category:"public health",time:24,subcategory:"door to door collection of garbage")
u=Sla.create(category:"public health",time:24,subcategory:"drains cleaning/overflow")
u=Sla.create(category:"public health",time:24,subcategory:"mosquito menace")
u=Sla.create(category:"public health",time:48,subcategory:"food adulteration")
u=Sla.create(category:"public health",time:24,subcategory:"garbage littering")
u=Sla.create(category:"public health",time:168,subcategory:"illegal slaughtering")
u=Sla.create(category:"public health",time:48,subcategory:"improper sweeping")
u=Sla.create(category:"public health",time:24,subcategory:"lifting of garbage")
u=Sla.create(category:"public health",time:48,subcategory:"provide or remove grabage bins")
u=Sla.create(category:"public health",time:168,subcategory:"quality of food in hotels")
u=Sla.create(category:"public health",time:168,subcategory:"stray animals")



u=Sla.create(category:"water supply",time:24,subcategory:"contamination of water")
u=Sla.create(category:"water supply",time:720,subcategory:"formation of new borewell")
u=Sla.create(category:"water supply",time:24,subcategory:"drinking water supply")
u=Sla.create(category:"water supply",time:48,subcategory:"issues related to public taps")
u=Sla.create(category:"water supply",time:168,subcategory:"repair bore wells")
u=Sla.create(category:"water supply",time:24,subcategory:"water pipe leakage")


u=Sla.create(category:"town planning",time:168,subcategory:"illegal construction")
u=Sla.create(category:"town planning",time:72,subcategory:"parking issues")
u=Sla.create(category:"town planning",time:168,subcategory:"removal of encroachments")
u=Sla.create(category:"town planning",time:72,subcategory:"issues realted to advertisements board")

u=Sla.create(category:"street lighting",time:72,subcategory:"non-working street lights")
u=Sla.create(category:"street lighting",time:168,subcategory:"provide new electric pole")
u=Sla.create(category:"street lighting",time:168,subcategory:"provide new street light")
u=Sla.create(category:"street lighting",time:48,subcategory:"removal of electric pole")


u=Sla.create(category:"engineering",time:1440,subcategory:"formation of new drain")
u=Sla.create(category:"engineering",time:1440,subcategory:"formation of new road")
u=Sla.create(category:"engineering",time:168,subcategory:"repair of drain")
u=Sla.create(category:"engineering",time:168,subcategory:"repair of road")
u=Sla.create(category:"engineering",time:720,subcategory:"public parks maintenance")



district=DistrictOffice.create(state:"Andhra Pradesh",district:"Guntur")

ward=WardOffice.create(district_office_id:1,ward:"1")
ward=WardOffice.create(district_office_id:1,ward:"2")
ward=WardOffice.create(district_office_id:1,ward:"3")
ward=WardOffice.create(district_office_id:1,ward:"4")
ward=WardOffice.create(district_office_id:1,ward:"5")
ward=WardOffice.create(district_office_id:1,ward:"6")
ward=WardOffice.create(district_office_id:1,ward:"7")
ward=WardOffice.create(district_office_id:1,ward:"8")
ward=WardOffice.create(district_office_id:1,ward:"9")
ward=WardOffice.create(district_office_id:1,ward:"10")


admin=AdminUser.create(name:"ajay",email:"ajay@gmail.com",password:"ajay",phone:"9999999999",designation:"district officer",municipal_id:"1",department:"Head")

admin=AdminUser.create(name:"sajay",email:"sajay@gmail.com",password:"sajay",phone:"9999999998",designation:"ward officer",municipal_id:"1",department:"deputy")
admin=AdminUser.create(name:"gajay",email:"gajay@gmail.com",password:"gajay",phone:"9999999997",designation:"ward officer",municipal_id:"2",department:"deputy")
admin=AdminUser.create(name:"rajay",email:"rajay@gmail.com",password:"rajay",phone:"9999999996",designation:"ward officer",municipal_id:"3",department:"deputy")


admin=AdminUser.create(name:"waajay",email:"waajay@gmail.com",password:"waajay",phone:"9999599995",designation:"supervisor",municipal_id:"1",department:"public health")
admin=AdminUser.create(name:"wajay",email:"wajay@gmail.com",password:"wajay",phone:"9999999994",designation:"supervisor",municipal_id:"1",department:"water supply")
admin=AdminUser.create(name:"eajay",email:"eajay@gmail.com",password:"eajay",phone:"9999999993",designation:"supervisor",municipal_id:"1",department:"town planning")
admin=AdminUser.create(name:"tajay",email:"tajay@gmail.com",password:"tajay",phone:"9999999992",designation:"supervisor",municipal_id:"1",department:"street lighting")
admin=AdminUser.create(name:"yajay",email:"yajay@gmail.com",password:"yajay",phone:"9999999991",designation:"supervisor",municipal_id:"1",department:"engineering")

admin=AdminUser.create(name:"ram",email:"ram@gmail.com",password:"ram",phone:"9999999990",designation:"supervisor",municipal_id:"2",department:"public health")
admin=AdminUser.create(name:"shyam",email:"shyam@gmail.com",password:"shyam",phone:"9999999980",designation:"supervisor",municipal_id:"2",department:"water supply")
admin=AdminUser.create(name:"manjeet",email:"manjeet@gmail.com",password:"manjeet",phone:"9999999982",designation:"supervisor",municipal_id:"2",department:"town planning")
admin=AdminUser.create(name:"diljeet",email:"diljeet@gmail.com",password:"diljeet",phone:"9999999983",designation:"supervisor",municipal_id:"2",department:"street lighting")
admin=AdminUser.create(name:"xxxx",email:"xxxx@gmail.com",password:"xxxx",phone:"9999999971",designation:"supervisor",municipal_id:"2",department:"engineering")






