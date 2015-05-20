package contentrenderingapp

import grails.converters.JSON
import grails.rest.RestfulController

class TimelineController extends RestfulController {
    static allowedMethods = [index: "GET"]
    static responseFormats = ['json']

    TimelineService timelineService

    TimelineController() {
        super(TimelineItem)
    }

    def index(TimelineCommand command) {
        List<TimelineItem> timeline = timelineService.currentUserTimeline(command)
        respond timeline
    }
}
