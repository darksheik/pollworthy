package com.pollworthy

class User {
  static hasMany = [polls:Poll,comments:Comment,responses:Response]
  String name
    static constraints = {
    }
}
