package ovh.garcon.api

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class FileController {

    static responseFormats = ['json', 'xml']
    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond File.list(params), model:[fileCount: File.count()]
    }

    def show(File file) {
        respond file
    }

    @Transactional
    def save(File file) {
        if (file == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (file.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond file.errors, view:'create'
            return
        }

        file.save flush:true

        respond file, [status: CREATED, view:"show"]
    }

    @Transactional
    def update(File file) {
        if (file == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        if (file.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond file.errors, view:'edit'
            return
        }

        file.save flush:true

        respond file, [status: OK, view:"show"]
    }

    @Transactional
    def delete(File file) {

        if (file == null) {
            transactionStatus.setRollbackOnly()
            render status: NOT_FOUND
            return
        }

        file.delete flush:true

        render status: NO_CONTENT
    }
}
