# Advent of Code

This contains my implementations for advent of code.
Plans for this year, from day three:

Check in each "phase" separately, so I can review my own thought processes.

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
