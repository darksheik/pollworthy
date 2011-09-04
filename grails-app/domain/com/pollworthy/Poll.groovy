package com.pollworthy

class Poll {
  static hasMany = [questions:Question,comments:Comment]
  static belongsTo = [user:User]
  String name
    static constraints = {
    }
  Map qmap = [:]

  def popQuestionMap(Long uid) {
    def u = User.findById(uid)

    this.questions.each {
       it.answers.each { 
         def r = Response.findWhere(user:u,answer:it)
         if (r) { 
              qmap[r.answer.question.id.toString()] = r.answer.id.toString()
            } 
       }
    }
    
  }
}
