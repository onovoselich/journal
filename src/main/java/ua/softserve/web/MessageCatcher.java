package ua.softserve.web;

import org.apache.tiles.AttributeContext;
import org.apache.tiles.context.TilesRequestContext;
import org.apache.tiles.preparer.ViewPreparerSupport;
import org.springframework.stereotype.Controller;

/**
 * Created by troll on 30.04.14.
 */
@Controller("messageCatcher")
public class MessageCatcher extends ViewPreparerSupport {
    @Override
    public void execute(TilesRequestContext tilesContext,
                        AttributeContext attributeContext) {
        String message = (String) tilesContext.getRequestScope().get("message");
        System.out.println(message);
    }
}
