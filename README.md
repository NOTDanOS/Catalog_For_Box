# Company Catalogue

## Catalogue that tracks customer names and their requests

My project is based on my father's company, which is a corrugated paper box company. For this project,
I'm going to be focusing on making a cataloguer specifically for his company. There will be at least 5
requirements that each customer must fill in to order from him.

The **PRODUCT REQUIREMENTS** are as follows:
- Quantity (number of boxes each customer needs)
- Dimensions (each customer will have their boxes custom-made)
- Design (has a few more subsections)
    - 3 Areas of Customization: **front & back, top & bottom, and left & right**
        - Walls: **Single or Double wall**. (Single walls are *thinner, lighter, and cheaper*. However, they are *weaker*
          than double walls)
        - Material: **Craft or Medium paper**. (like single walls, they are *lighter and cheaper*, but are weaker
          than **medium paper**)
        - Area density: **250, 200, 150, 125, 100**. (the *higher* the number, the *more* objects it can hold)
- Time of delivery: date when product must be delivered (Each company will be arranged in terms of earliest delivery)

On the catalogue, the customer will have to pick between these options, to which later their product will be added to
the list of requests on the company's computer database. They will be arranged in according to the earliest time of
delivery to the latest.

## User Stories
- As a user, I want to be able to choose what features to add onto the product
- As a user, I want to see all the choices I made for my product
- As a user, I want to type certain answers when prompted
- As a user, I want to know what part of the product I'm customizing (front, left, right, etc.)
- As a user, I want to be able to change parts of my product that I don't like with ease.
- As a user, I want to be able to save my current products I've already made.
- As a user, I want to be able to load back and see what products I already assigned.
- As a user, I want to be able to see my orders based on date (in ascending order).

# Instructions for Grader
- You can generate the first required action relating to adding X's to a Y by the IdentificationUI, where you basically
add or remove information according to your choice.
- You can generate the second required action relating to adding X's to a Y by the FinalUI, where you can see that
the information is automatically sorted by date. You can also sort based on other things as well, like alphabetical
order if you want. 
- You can locate my visual component by IdentificationGUI, where you see an arrow jpeg in place of a button.
- You can save the state of my application by pressing Save & Close button on SaveOrAddUI.
- You can reload the state of my application by pressing Load button on the IntroUI.

## Steps to use my GUI
- Start at Main, which will then go to IntroUI. Click no to start over or yes, if you want to use a pre-existing file.

- Another UI will appear: IdentificationUI. Type it according to instructions (that means date format too). 
Don't forget to save by clicking the button on the left of each text field.
Then press bottom left arrow to move to next UI. (That arrow is my visual component)

- DesignUI will consist of 3 smaller frames. First, you'll be designing the front and back of your box. 3 drop-down
menus will be there. Left is the sheet, middle is material, and right is area density. You'll do this 3 times, each
time for a different part of the box (front and back, top and bottom, then left and right). Each choice made will save
all those design choices to a design class, which will later be transferred to table. To move to next, click save.

- SaveOrAddUI will appear next. That will allow you to choose whether to add another box to your list, or just move on
and end it the customization process and also move to the next UI.

- Finally, FinalUI will appear. This will show a table that shows all the choices you've made for your product. To
close the project, close the window. If you restart, you'll be able to reload your past progress by clicking "Yes" on
IntroUI.

# PHASE 4: TASK 2
- Wed Aug 09 16:55:36 PDT 2023: New inventory list made by user.
- Wed Aug 09 16:55:39 PDT 2023: Personal/company name set by user.
- Wed Aug 09 16:55:41 PDT 2023: Quantity of boxes set by user.
- Wed Aug 09 16:55:43 PDT 2023: Shipping location set by user.
- Wed Aug 09 16:55:52 PDT 2023: Date of delivery set by user.
- Wed Aug 09 16:55:54 PDT 2023: Material for Top and Bottom wall set/changed by user.
- Wed Aug 09 16:55:55 PDT 2023: Sheet for Top and Bottom wall set/changed by user.
- Wed Aug 09 16:55:56 PDT 2023: Area Density for Top and Bottom wall set/changed by user.
- Wed Aug 09 16:55:57 PDT 2023: Sheet for Left and Right wall set/changed by user.
- Wed Aug 09 16:55:58 PDT 2023: Design of box set by user.
- Wed Aug 09 16:55:58 PDT 2023: Inventory added to list with choices set by user.

# PHASE 4: TASK 3
Perhaps if I just used one UI instead of multiple UIs. That way, I wouldn't have to constantly carry fields from one
class to another; I can just use the current one in the class. For example, I constantly am carrying an InventoryList 
throughout the classes. In addition, I wouldn't have to call a field each class; I can probably just make it local 
based on the method instead.

Citations:
JSON tests and layout was taken from JSONSerializationDemo-master uploaded by Paul Carter
Event and EventLog was taken from AlarmSystem uploaded by Paul Carter