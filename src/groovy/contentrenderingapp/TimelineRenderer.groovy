package contentrenderingapp

import grails.rest.render.ContainerRenderer
import grails.rest.render.RenderContext
import groovy.json.JsonBuilder
import groovy.xml.MarkupBuilder
import org.codehaus.groovy.grails.web.mime.MimeType
import org.springframework.http.HttpStatus

class TimelineRenderer implements ContainerRenderer<List, TimelineItem> {

    @Override
    Class<List> getTargetType() {
        List
    }

    @Override
    Class<TimelineItem> getComponentType() {
        TimelineItem
    }

    @Override
    void render(List timeline, RenderContext context) {
        context.contentType = MimeType.JSON.name
        //context.status = HttpStatus.OK

        def builder = new JsonBuilder()

        builder.call(
            [items: timeline.collect { TimelineItem timelineItem ->

                def domainInstance = timelineItem.item

                return [
                    date: timelineItem.date,
                    type: domainInstance.class.simpleName,
                    item: [
                        id   : domainInstance.id,
                        value: domainInstance.toString()
                    ]
                ]
            }]
        )

        builder.writeTo(context.writer)
    }

    @Override
    MimeType[] getMimeTypes() {
        [MimeType.JSON] as MimeType[]
    }
}
