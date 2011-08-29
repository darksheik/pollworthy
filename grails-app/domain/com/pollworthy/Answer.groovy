package com.pollworthy

class Answer {
  static hasMany = [responses:Response]
  static belongsTo = [question:Question]
  String text
    static constraints = {
    }
}
