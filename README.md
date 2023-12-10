# Advent of Code

This contains my implementations for advent of code.
Plans for this year, from day three:

Check in each "phase" separately, so I can review my own thought processes.

## 2023, Day 20, phase two

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
