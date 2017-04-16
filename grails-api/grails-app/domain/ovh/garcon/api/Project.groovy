package ovh.garcon.api

class Project {

    String title
    String desc
    String img
    String url
    String group
    String content

    static hasMany = [contributors: Contributor]

    static constraints = {
    }
}
