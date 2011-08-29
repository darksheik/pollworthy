package com.pollworthy

class Question {
  static hasMany = [answers:Answer]
  static belongsTo = [poll:Poll]
  String text
    static constraints = {
    }
}
