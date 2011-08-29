package com.pollworthy

class Poll {
  static hasMany = [questions:Question,comments:Comment]
  static belongsTo = [user:User]
  String name
    static constraints = {
    }
}
