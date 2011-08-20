package com.pollworthy

class Comment {
  static belongsTo = [Poll,User]
  String content
    static constraints = {
    }
}
