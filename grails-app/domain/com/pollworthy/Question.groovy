package com.pollworthy

class Question {
  static hasMany = [answers:Answer]
  String text
    static constraints = {
    }
}
