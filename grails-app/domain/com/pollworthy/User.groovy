package com.pollworthy

class User {
  static hasMany = [polls:Poll,comments:Comment]
  String name
    static constraints = {
    }
}
