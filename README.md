# Propositional Logic
Currently, the program takes a well formed formula as a String in input and forms a formula tree out of it, containing propositions and operators as nodes.
I've also made a class for valuation, which stores the values of the propositions, and a function in the tree that checks if the given valuation satisfies the wff or not.
Finally, there's a function to check if the formula is satisfiable or not, by running through all relevant valuations, till it finds one that satisfies the formula. 
It can also check the satisfiability of the given formula by Semantic Tableaux.

