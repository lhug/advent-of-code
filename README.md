# Advent of Code

This contains my implementations for advent of code.

## 2024, Day 12, phase two

Nope. Fuck it. Stole entire code from https://github.com/imisaacwu/AdventOfCode2024/blob/main/src/day12.java.

## 2024, Day 12, phase one

Overengineering at it again. Also, my stupid head.
I started out trying to gather all required coordinates etc.
Until I figured out, that I do not need any coordinates, except to know where to start,
I was going mad slowly. After that, I only required to fix an off-by-one error.

## 2024, Day 11, phase two

This I did not understand. So I had to get help from an AI.
My approach was to find mathematical rules to apply here, to basically
create a shortcut to the solution. That was, however, not the correct way
to go about this.
After having the AI generate some code for me (which initially did not work),
I went over it to examine the finer details.

Turns out, the AI Code - despite being much faster than my code - does not do much different.
The main benefit of the AI code is the introduction of frequencies. All numbers
are still calculated as required, no step is being removed, however the massive difference
lies in the fact that my idea was to just keep all the numbers in a List (or Stream) and just reiterate and extend,
while the AI code just keeps a map handy to store the amount of numbers. Which means the max site of the Map is not
"final amount of numbers" (in my case, 272673043446478), but rather "amount of unique numbers in step".
Which is obviously WAY lower :)

## 2024, Day 11, phase one

Not much to say about this, to be honest. First task relatively straightforward.
List of numbers, apply rules, count.

## 2024, Day 10, phase two

This is ridiculous. I accidentally created the solution for part two while implementing
part one. And when did I realize this? AFTER solving part two AGAIN. Not only did I
overengineer this solution like mad, I also wasted HUGE amounts of time figuring out an
unnecessarily complicated solution, which I reduced until it was the same as right
before I solved part one. This is sooo embarrassing.

## 2024, Day 10, phase one

Terror! I mean, initially. Recursion requires understanding recursion :)

## 2024, Day 9, phase two

So. Many. Errors.
And all so silly. Defragmentation to the right, counting off by one... all thos silly things.
Luckily, I have tests to tell me where I am going wrong :)

## 2024, Day 9, phase one

Silly mistakes. Confuse current count and index, forget that there are more than 9 numbers..
However, once that was finished, everything is a-go.

## 2024, Day 8, phase two

This was surprisingly easy. Am not cleaning this up anymore, as this is a wonderful example
of "how to extend simple functionality".
Everything was already there, the only requirement change was not to stop after the first step.
Overengineering paid off here :)

## 2024, Day 8, phase one

Hooray for overengineering :)
I was so worried about the antinodes until I realized that I do not need to mark anything.
I just need to check if, for every antenna that has a pair, is the "opposite" part on the grid.
Since I had just extended my Coordinate and Direction classes, this was relatively simple.
Fun to think of how to test this :)

## 2024, Day 7, phase two

I did almost see that coming. While I did anticipate a third operator, I was too fixated
on the "has multiple solutions" part of the explanation.

I ran the modified code on the puzzle input, and it took about 15 seconds.
I modified it a lot - more parallelization, ignoring "all" solutions and only finding valid
equations - and it runs a lot faster. Still about 3 seconds, but much less than before.

## 2024, Day 7, phase one

Thought long and hard about this. Did not find a useful solution.
Had to brute force. I am completely unsure how the decision process for the
operator works.
Wasted a lot of time because I - again - misunderstood the assignment.

## 2024, Day 6, phase two

This took some thinking and remodeling.
However, realizing two things made this easy:
I only need to check locations where the guard initially went, and I need to extend the
"distinct coordinate" check by the direction, as stepping on the same point in the same
direction leads to a loop.

The only downside is that this was a bitch to test. :)

## 2024, Day 6, phase one

Similar to Floor is Lava, but currently less difficult.
I did, however, fuck up the directions in Floor is Lava, so I had to fix those.

## 2024, Day 5, phase two

I learned something about topographical sorting today.
I admit, I had help from ChatGPT, as I was at a complete loss how to apply the rules.
Learning about Khans algorithm was fun, and I do love this kind of stuff.
I wonder if there is a better way to implement this algorithm than I did.

## 2024, Day 5, phase one

Looks much more complicated than it is. I fell into the overengineering trap again, as one does, however this does
appear to put me on good footing when it comes to phase two.
Also, this was the first time the AI Plugin did not annoy the hell out of me. It almost was correct sometimes.

## 2024, Day 4, phase two

After much, MUCH overthinking on how to solve this, I finally got hung up on a wrong solution.
Not only did I di the correct thing, which is finding the diagonal MASes, I did not read, but interpret the assignment.
And as X-MAS is basically CROSS-MAS, I took the straight X-MAS as well.

I spent roughly 30 Minutes figuring out what was wrong, until I finally re-read the instructions.
This could have been so much easier ^^

## 2024, Day 4, phase one

I hate grids. But, I managed. Most likely overcomplicated things a lot.
But then again, it does work.
However, having already seen the phase two assignment, I think I might just as well stop here :)

## 2024, Day 3, phase one and two

Head swimming, am sick today. However, simple enough - regex match, filter and sum.
Phase two had me stumped because I spent an hour (!) realizing that as soon as I add handling for do and don't,
I need to remove those from phase one.

## 2024, Day 2, phase two

Damn that took some time. Last years' overflow made me avoid brute forcing the solution. However, all patterns I found
were insufficient, I always came up short.

So I rethought the entire thing. Checking for correctness was WAY too complicated, so I reduced that a lot,
and then just... well... brute forced the solution.
Do not feel good about this, but at least I got the solution right :)

## 2024, Day 2, phase one

Reading is a very useful skill. I wasted a lot of time with the comparison, while completely missing the extra requirements of "same difference" and "fail on zero".
Let's hope phase two works better for me :)

## 2024, Day 1, phase two

Luckily, I did not do too much magic (though still overcomplicated things) with the parsing.
Lists were quite useful. I am very certain I could have done better, but this was after a looong day at an amusement
park with 2 kids, so I guess I may be forgiven for sloppy craftsmanship here :)

## 2024, Day 1, phase one

This was interesting. The most difficult part here for me was to actually parse the lists.
From that point forward, it was relatively straightforward - sort, subtract, ensure positive value and add.

## 2023, Day 16, phase two

This was actually much simpler than before. It was rather obvious that the initial point would change,
however checking all configurations was not anticipated.
I feared this would run vor at least a minute, as the input is not that small, but apparently,
using arrays is relatively fast in Java.

## 2023, Day 16, phase one

After having a long, LONG, thought about how to do this, I settled on recursion.
This is not my forté and I had to retry a lot. Also, I do not really know how to test recursion,
so there are basically zero tests here.

However, I did learn something profound, that is so obvious I never thought about it.
Using java Streams in recursive calls is VERY dangerous, as the initial call only returns
when the entire recursion stack is processed. This led to multiple StackOverflow errors on my end.
Interesting to know.

## 2023, Day 15, phase two

En exercise in reading. Apart from that, the solution is relatively simple.
The wording was a little strange and confused me, but after two failed attempts,
one of them being a typo, the solution was done.

## 2023, Day 15, phase one

This was rather simple. Easy hashing, good situation to use reduce.

## 2023, Day 14, phase two

An exercise in frustration. Again, I do not really understand why my initial approach did not work.
The initial idea was:
- twist matrix, until first repetition
- reduce loop amount by repetition index (i.e. reduce by n steps)
- modulo the shit out of the remaining iterations
- check the number at the appropriate index of the already recorded loop
- profit

So, for example, given `1234534534534534` as the actual loop (each number representing a matrix configuration),
the iteration would have found the loop after 6 steps, having cached 5 entries.
This would mean: the loop size is 3, the loops start at step 3.
Meaning further, there are 14 looping steps (16 - 2), the loop length is 3, modulo-ing 14 by 3 gives
Having already recorded `12345` in the initial list, the start index would be 2, so returning the value at
index `repetitionIndex + moduloResult - 1` SHOULD have given the correct "final" step of the number line.
This was, however, not the case.

Doing the same thing in a `for`-loop, and just skipping roughly `(1_000_000_000 - foundIndex) / loopSize)` entries,
and then manually computing the final steps, for SOME reason, leads to the correct result.
At least I am done wth this, now.

## 2023, Day 14, phase one

Interesting problem. My initial attempt at solving, which was trying to be smart
by just calculating instead of actually moving, was unsuccessful.
So I resorted to do an in-place replace during a single iteration, which worked out
fine.

## 2023, Day 13

This wqs a fun exercise. Sadly, I had to do this to the side, so there was no time to
actually split the uploads.

I had a lot of trouble thinking about the second phase, because the changes did not remove
the original mirror lines, meaning the test input would have had a smudged *and* a clear
mirror line.

However, the task explicitly called for checking the one smudged result, and not all possible
results, making the second task much simpler than I thought.

The "errorCount" variable was added to check for the smudge.
Before this, the equality check was just `Objects.equals`.

## 2023, Day 12

I had zero idea how to implement this. I actually struggled to do this manually. I then found a solution
and spent a lot of time trying to understand how it works.
I will add more documentation (for myself at least) to show what it does and then try to understand it.

I linked the used solution instead of mine today, because this was completely stolen.

## 2023, Day 11, phase two

I knew it! Base idea of being able to extend further than just twice was exactly what I needed.
Sadly, the size was so extreme, that the initial approach did not work.
So I had to rework anyway :)

## 2023, Day 11, phase one

The biggest problem I had was the expansion of the columns.
The rest was relatively simple. Here's hoping that part 2 will be as simple.

## 2023, Day 10, phase two

Again, reddit was my savior here. I am not that versed in matters of geometrics.
I have never before heard of Picks Theorem, or the Shoelace formula. Luckily, this
is not a challenge to show how great one is, but to learn.

I made a lot of mistakes, for example, mistaking the nodes (used for the shoelace formula)
with the boundary lattice points.

For reasons I do not understand, manually collecting all coordinates along the way (which I tried first)
ran WAY longer than the mess of hoops I am jumping now.

I have seen an elegant solution for the mapping in haskell, basically translating any symbol into modifiers (i.e. '|' becoming (0,2) transform).
I had that idea, but my caution in not being able to distinguish if this must be + or - 2 led me to choose a different path.

## 2023, Day 10, phase one

I am very certain that there is a better way to do this.
However, I am content with this, as it does follow and "optimizes" for not following a single line
and then dividing the steps in half.

## 2023, Day 9, phase two

Well... that was easy. I don't think my initial thought would have had a problem here, but
the fact that it was off by eight means that this approach is definitively better.
Since reading the sequence from right to left and then following the same approach as before made this
rather simple.

## 2023, Day 9, phase one

I had a little time today and did some optimizations.
The first step I took was to keep track of all values, to be prepared for any kind of
"yes, but what if" shenanigans, but I realized that it would be very unlikely to insert such a condition into each puzzle input.
Contrary to day eight in which there was a clear pattern, this would not be as simple, so I optimized for recurstion.

I am unsure why my manual approach was off by eight, though.

## 2023, Day 8, phase two

God dammit. Well, to look at the bright side: I learned a thing or two about parallel processing.
For example, I had no idea what a CompletionService is, or how to actually use Futures.
It all turned out to be quite useless, though, as - since the required amount of steps was in the
billions (or trillions for uncultured people :P) this was not doable in a reasonable amount of time.

So, for the first time (yes, I did day five on my own) I had to turn to reddit, because I did not spot
the pattern, despite having prepared for EXACTLY THAT in the first step.
Shame on me.

## 2023, Day 8, phase one

Simple mapping. That was fun. And luckily, I did anticipate the fact that not all steps will be
the same letter three times.

## 2023, Day 7, phase two

I was right, only in the wrong way.
This took a lot more rework than I'd have liked. The "simplicity" of having the game comparable
has turned out to be a liability.
Also, the card order needed to be redone entirely.
The solution works, but can be improved. Maybe when all is done, I'll get to that,
but then again, it works and does so correctly.

## 2023, Day 7, phase one

More fun! I have rarely had to work with comparators, but so far, everything works as intended.
Maybe too many test cases, but I have had edge cases ruin my day before, so better to include
as many edge cases as I think are reasonable.

I think that part two will contain slight differences in ordering, so I chose to not order the list automatically.

## 2023, Day 6, phase two

The modification was also very simple. Not much to do except change the parser.

## 2023, Day 6, phase one

What a relief after day five. This was rather simple, a little bit of math and calculations, and
actually understandable puzzle inputs.

## 20223, Day 5, phase two

I hate it. I am not sure why, but this took me WAY longer than it should have.
At every turn there were minor inconveniences. After a certain point, I was unwilling to change
my data model, which, in retrospect, would potentially have helped. Now this works, and it doesn't
even take too long, less than a second, but still.

I definitively have learned something here, about matching ranges, and I am very certain that
this, too, can be done better. But I am finished and all tests pass.
So.

## 2023, Day 5, phase one

Annoying, annoying, annoying.
After programming around and finding wonderful solutions for integers, I ran the actual test input and
everything immediately crashed and burned, as the values were slightly too large to be accommodated in an int.
Then, after painstakingly changing everything to Long, the application crashed due to memory issues.
Who would have suspected that writing multiple maps with several million entries may be a bit too much?

So, I ended up scrapping the entire thing and created utility classes. This helped a lot.

## 2023, Day 4, Phase two

Okay, so my expectation was not met. However, the calculation was relatively easy to make.
I did, however, stumble around A LOT.
This is what happens when one is used to testing everything and has no clear idea
where to test from here. Now that I have my solution, I am certain I could devise a way
to test this correctly, apart from the pure "count matches" and "sum correctly" tests I added.

I am not happy with my result, but I am currently too aggravated over my own stupidity that I don't
feel like cleaning it at all.

## 2023, Day 4, Phase one

Oh boy, this looked so simple, but many tiny pitfalls along the way.
Sudden empty Strings while converting numbers, buffering spaces etc...

However, I fully expect that phase two will make use of either the game ids or the
unmatched numbers. We'll see.

## 2023, Day 3, Phase two

Oh my, my initial thought of "the symbol will be relevant later on, just add it to the record" was obviously good.
After some minor refactoring to make the actual logic a little more legible, implementing the new requirements was
relatively simple.

A lot of improvements are obviously possible, for example there is most likely a better way to calculate the indexes
and to avoid duplicating the found numbers, but I am reasonably content with this - it works, and it is relatively
easy to understand what it does, I think.

I hope my opinion on this will not change much when I look back at this in 2024.

## 2023, Day 3, Phase one

Horrible, horrible problem, as I rarely work with problems like this.
Let's see where this goes. I completely expect this to require the "free" numbers
in phase two, but let's see where this is going.

## 2023, Day 2

Expected to have to manipulate the expected values, too.
Was not the case. Luckily, I separated the extraction from the processing in a way that made
examining the data very simple. This made part two actually simpler, as no re-thinking was required.

## 2023, Day 1

Man this was annoying. I actually tried to examine the input by character in phase one, however that quickly became
too much work when phase two came around.
Rewrote the entire thing to work with regex-matching.
The solution is currently too complicated, maybe I will optimize later.

What was REALLY annoying was that the test data did not contain a simple `twone` or similar as input,
as this would be `21` and not `22`, requiring a change in mather processing.
