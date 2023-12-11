# IbisRepo
Team Ibis's Gymnastics Coach Lesson Planner

# Description
Welcome to our GymProject application! This software was made possible by the hard work of our developers. It is intended for private use and not allowed to be open sourced. 

# Overview
The intended use of this software is to allow a gymnastics coach to create a lesson plan with card objects. These cards are made by our client and she has been selling them as packs. We are able to manipulate these cards in our lesson plan in order for the user to make a customizable plan. After they are done selecting, they can print to pdf the lesson plan. 

# Keynotes
* Each card is part of a card object, we load them in through as CSV reader and assign iterate through them into a list of cards.
  
* We first make an Event in order to drag our cards into the lesson plan.

* When loading in a new pack, you should be able to follow the standard for the first two, without making it dependant on the 
name of the card (Demo) pack.

# Bugs 
* The back button on the print preview makes the cards disappear until you put a card back in, then they reappear
* If the card is on the side, the zoom feature will make it clip through the page
* If you have a lesson plan, and you click save, it will put the all the cards in the first event
  
