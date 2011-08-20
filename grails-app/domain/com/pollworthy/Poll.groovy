package com.pollworthy

class Poll {
  static hasMany = [questions:Question,comments:Comment]
  static belongsTo = User
  String name
    static constraints = {
    }
}
