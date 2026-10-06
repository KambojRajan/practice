Requirements
- The Cache must add a new <k, v> pair to it and must mark it most recently used
- The Cache must have a capacity for the size of <k, v> pairs
- If the Cache is full at some point and a new <k, v> pair needs to be inserted a victim must be chosen(the LRU pair)
- adding, updating and getting a key's value should be O(1) time complex
