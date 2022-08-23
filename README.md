# FamPay-Android-Assignment
Assignment for applications to Android Developer roles at FamPay

⏰ **Deadline** : 48 Hours from receiving assignment

# Details

- Develop a standalone container, that displays a list of `Contextual Cards`
    - A `Contextual Card` is used to refer to a view that is rendered using json from an API
    - These views are dynamic and their properties like images, color, texts, buttons (CTAs) etc. can be changed from backend at anytime.
- This container should work completely independently of everything else, such that, we can add this to container to any fragment/activity and it should work. (Plug-and-Play component)
- Your app should render contextual cards in a list based on the API response that you get from this [API](https://run.mocky.io/v3/fefcfbeb-5c12-4722-94ad-b8f92caad1ad)
- The design specifications for the different design types can be referenced from design specified [here.](https://www.figma.com/file/AvK2BRGwMTv4kQab5ymJ0K/AAL3-Android-assignment-Design-Specs) (To access and download the assets please login into Figma)
- The design linked is only for reference for different types of design types of cards. Actual response from API will render different results.

`Note` : The FamPay logo at the top will not be a part of the container, its a part of the activity/fragment that encloses the container. The Container will only contain list of contextual cards

## **Expected Deliverables:**
- All deeplinks (card, CTAs, Formatted Text entities) should be handled.
    - In the API, almost every component has its own url/deeplink that is used to trigger action on click.
- On long press of Big Display Card (HC3), it should slide to the right and display action buttons as per the design
    - On tapping "remind later" action on a card, it should be removed from the display. This card should be shown on the next app start.
    - On tapping "dismiss now" action, it should be removed from the display. This card should never be visible again.
- Implement swipe down to refresh feature
- Handle loading and error states
- Write structured code with flexible and reusable components
- Design should match as closely as possible to the one on Figma
- We will test how the submission performs for different API responses, which will include different texts, colors, gradients, types, ctas, images etc. that will cover all the possible cases.
    - How well the submission performs across all these cases will be noted, so try to consider all the other cases, not included the mock API, that might exist based on the API response schema.

## Schema
- **Formatted Text**
    - `text` - text to parse
        
        e.g. `"Hello {}, you look lonely! Why don't you do {}!"` 
        
        the two `{}`s each represent an entity and would be substituted with attributed text, constructed from `entities`. 
        
    - `entities` an ordered array of **[Entity](https://www.notion.so/AAL2-Android-Assignment-44898d1d62a44a0ead628883befed5c7)**
- **Entity**
    - `text` - text to display in place of `{}`
    - `color`  - optional, hex color code for the label of this entity
    - `url` - optional, the URL/URI to trigger upon clicking this entity's label
    - `font_style` - optional, can take values such as `underline` and `italic`
- **Card**
    - `name` - name of the card (for reference only, not used in the UI
    - `formatted_title` - optional, a title, refer to **Formatted Text** for structure
    - `title` - optional, a fallback title in case the `formatted_title` cannot be parsed
    - `formatted_description` - optional, a description, refer to **Formatted Text** for structure
    - `description` - optional, a fallback description in case the `formatted_description` cannot be parsed
    - `icon` - optional, refer to **Card Image** for type ****
    - `url` - optional, the URL/URI deep link to open on clicking anywhere in the card (except for a URL-attributed-entity)
    - `bg_image` - optional, refer to **Card Image** for type
    - `bg_color` - optional, hex code for background color
    - `bg_gradient` - optional, refer to **Gradient** for type
    - `cta` - an array of objects of type **Call to Action** for type
- **Call to Action (CTA)**
    - `text` - text of the button
    - `bg_color` - optional, hex code of background color of the button
    - `url` - optional, the url/uri scheme to trigger upon button click
    - `text_color` - optional, text color of the button
- **Gradient**
    - `colors` - an array of hash-prefixed ARGB color hex codes
    - `angle` - an angle (assume 0 if `null`) for the gradient
- **Card Image**
    - `image_type` - possible types - `asset` and `external`
    - `asset_type` - optional, a string containing the name of the asset name (present only if `image_type` is `asset`)
    - `image_url` - optional, the url to the image if type is `external`
- **Card Group**
    
    A card group is a **row** of card(s). 
    
    - `design_type` refer to the enum below. You can take a look at the [design](https://www.figma.com/file/9chjT8NkzIvXRhbQcuScow/Android-assignment-Design-Specs?node-id=0%3A1) to find designs for each case.
        
        ```java
        public enum DesignTypes {
            SMALL_DISPLAY_CARD("HC1"),
            BIG_DISPLAY_CARD("HC3"),
            IMAGE_CARD("HC5"),
            SMALL_CARD_WITH_ARROW("HC6"),
        		DYNAMIC_WIDTH_CARD("HC9")
        }
        ```
        
        - `NOTE` : `HC9` is a special type of card, its width is not predefined. Its height is equal to height specified in its parent card group, and width is dynamic and depends on the size of the `bg_image` of the card.
    - `name` - a name for the [card group](https://www.notion.so/c87138eb64b54c26a15755d57b8d7566) (for reference purpose only)
    - `cards` - an array of [card](https://www.notion.so/c87138eb64b54c26a15755d57b8d7566) objects
    - `height` - height of the included cards in "dp" **(only used for HC9)**
    - `is_scrollable` - if this property is true, then the card group is shown as a horizontal scroll view. If this property is false, then all cards are accommodated into the screen width itself with equal width for each card. **(not used for HC9)**

The API response gives you a list of **Card Group** objects.

Ignore extra params that may come with the API response, that have not been defined here

## Resources
- API : [https://run.mocky.io/v3/04a04703-5557-4c84-a127-8c55335bb3b4](https://run.mocky.io/v3/04a04703-5557-4c84-a127-8c55335bb3b4)
- Design: [https://www.figma.com/file/AvK2BRGwMTv4kQab5ymJ0K/AAL3-Android-assignment-Design-Specs](https://www.figma.com/file/AvK2BRGwMTv4kQab5ymJ0K/AAL3-Android-assignment-Design-Specs)

# Submission **Instructions**
- You may use kotlin or java, as you prefer
    - **DO NOT USE** cross platform frameworks like flutter, react-native etc.
- Submission should be a GitHub repo with a proper README with relevant instructions.
    - Add the apk file in the repo itself.
- Try and keep git commit messages clean.
- Keep variable names relevant to what they are used for.
- Follow indentation practices and keep code clean.
- Add comments in code if something might be confusing for the person evaluating your code

**Feel free to ask any doubts!**

## Best of Luck!
