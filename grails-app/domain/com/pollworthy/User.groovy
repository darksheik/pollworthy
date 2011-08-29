package com.pollworthy

class User {
  static hasMany = [polls:Poll,comments:Comment,responses:Response]

String name
String email
String password
String toString()
{ "$name" }

def constraints =
{
name(name:true)
email(email:true)
password(blank:false, password:true)
}



}
