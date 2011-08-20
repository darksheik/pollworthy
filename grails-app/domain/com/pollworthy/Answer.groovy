package com.pollworthy

class Answer {
  static hasMany = [responses:Response]
  static belongsTo = [Question,User]
  String text
    static constraints = {
    }
}
