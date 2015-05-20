package contentrenderingapp

import grails.transaction.Transactional
import groovy.json.JsonBuilder

@Transactional
class TimelineService {

    List<TimelineItem> currentUserTimeline(TimelineCommand command) {
        Item item1 = new Item().save()
        Item item2 = new Item().save()

        def tl1 = new TimelineItem(item: item1, date: new Date()).save()
        def tl2 = new TimelineItem(item: item2, date: new Date() + 10).save()

        [tl1, tl2] as List<TimelineItem>
    }
}
