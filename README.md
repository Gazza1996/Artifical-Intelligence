| Project Details   |     |
| --- | --- |
| **Course** | BSc (Hons) in Software Development  |
| **Module** |  Artifical-Intelligence |
| **Student** | Gary Mannion |
| **Lecture** | John Healy |

## Features
### Fuzzy Logic 
The Fuzzy logic is being used to take the strength of a spider. Each spider will start 100 venom and this should descrease when they attack a player. The fuzzy logic will decide how sever the attack is. The venom will also be determined and the fuzzy logic should then decide how much damage a player will take.

### Encog (NN)
Using encog for the spider state while in play. Once the NN is trained it will decide on the state of it after values are passed through. I also have added states for hiding spots and a healing state for when it is at a spot. An enum is in place to only allow one action at a time by the spider. 

### Heuristic Search
After doing some extra research i found a heuristic search called manhatton distance which i felt would be a good fit in this maze. It basically checks an array where the spider is standing on a tile, will then check points of the neighboring tile, it will then check to see which tile is closer to the player and perform an action. I have implemented this action every 2 seconds due to the threading i am using.

### Multi-Threading
Each spider that is created will create a new thread and use an executor to keep using that thread for certain methods. I have it set to repeat every 2 seconds like the heuristic search.

## References
https://stackoverflow.com/questions/8224470/calculating-manhattan-distance
