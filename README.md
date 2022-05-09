# RPS_ai
 
This application is an attempt to introduce myself to machine learning by creating an AI to play rock,paper,scissors.
I chose rock, paper, scissors for a few reasons:
1) It is trivial to code
2) It is easy to evaluate if the AI works. It is well known that if one randomly selects rock, paper, or scissors with equal likelihood on each move, they will have a 1/3 win rate regardless of any strategy that the opponent employs. Therefore, a winrate of less than 1/3 against a specific strategy indicates room for improvement. 
3) Computers excel at the very things that make RPS hard for humans. Namely, the pseudorandom algorithms are far more effective for computers and computers can process large amounts of information and search for patterns in the blink of an eye.

The algorithm:
At the start of every round, the AI has an equal probability to choose rock, paper or scissors. Then the AI looks at past rounds from the session and weights those probabilities based on how often the opponent has played each option. The AI then considers the last move played and further weights the probabilities based on the option picked given that it was preceded by the last move. This process is repeated until the sequence of the last 4 moves is considered. Then a random number is generated and the AI picks its move accordingly.
The more games that are played, the more past habits will affect the shift of probabilities. 

There are two versions of the game in this repository: a console based one found in main.Driver and a GUI based one found in gui.GUI.

It is recommended that users only download the file RPS.jar. The packaged application contains functionality not present in the published source code.
