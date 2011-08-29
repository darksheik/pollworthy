package com.pollworthy

class Comment {
  static belongsTo = [poll:Poll,user:User]
  String content
    static constraints = {
    }
}
