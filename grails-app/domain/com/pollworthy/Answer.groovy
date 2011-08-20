package com.pollworthy

class Answer {
  static belongsTo = [Question,User]
  String text
    static constraints = {
    }
}
