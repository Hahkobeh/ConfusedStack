# ConfusedStack
Project for Data Structures and Algorithms class - Implements a stack which reads commands pop, push and top from a txt file while also fulfilling various exceptions to normal functioning


# Program is run by:

javac ConfusedStack.java \
java ConfusedStack input.txt output.txt 

# Assignment specifications:

The problem: a confused stack\
The task your system has to perform is to implement a stack of natural numbers. In general, the operations push, pop, and top should behave as normal for a stack, with the following exceptions:

push(0) should not result in any change of the stack, except if the stack is empty, in which case it adds 0 to the stack\
push(666) adds not just once 666 to the stack, but it adds it 3 times\
If the top element of the stack is 666, pop() does not just remove it from the stack (and outputs it), it also removes the next\ element from the stack, without outputting it (if it exists).\
If the top element of the stack is 666, top() does not output 666, but 999.\
If the top of the stack is 7, pop() does not remove it from the stack, but just outputs 7.\
If the top of the stack is 7, top() does not output 7, it just removes it from the stack.\
push(3) does not add 3 to the stack, but instead 7.\
If the top of the stack is 319, then applying top() outputs 666.\
push(13) first empties the stack (outputting each element in the sequence a series of pops by a non-confused stack would do) and then enters 13 on the stack.\
If the top of the stack is 42, pop() removes all elements of the stack, only outputting 42.\
Input file\
An input file consists of a sequence of push, pop and top commands, each command in its own line.

Output file\
Each output from the program is written to the output file, followed by moving to a new line (so each element is in its own line). If the program encounters a command that is not push, pop or top, it outputs:\
Input error.\
and terminates. If the argument of a push command is not a natural number, it outputs:\
Imput error.\
and terminates. If the above does not say otherwise, performing top() on an empty stack should result in an output of\
null\
and the program continues. Performing pop() on an empty stack should result in the output\
Error\
and the program should terminate.
