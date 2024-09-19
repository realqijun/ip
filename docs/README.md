# BabyGronk User Guide

BabyGronk is a **chatbot task manager** with a GUI. Designed with simplified commands and a Gen-Z personality, 
this chatbot supports the creation, deletion, update and finding of tasks.

- [Quick Start]()
- [Features]()
   - [Creating a task]()
   - [Deleting tasks]()
   - [List all tasks]()
   - [Find tasks]()
   - [Mark task]()
   - [Unmark task]()
   - [Greeting options]()
   - [Exiting]()

![Product screenshot](Ui.png)

  ## Quick Start 

  ## Features
Notes: 
- Commands are not case-sensitive
- `...` means there can be more
- Some commands have abbreviations or alternatives , they will be described with the format `[command]/[abbr]/...`


  ## Adding tasks

There are 3 types of tasks you may add:
1. [ToDo]()
2. [Deadline]()
3. [Event]()

### ToDo
ToDo tasks are simple tasks that can be done anytime you wish.\
Usage: `todo [taskName]`
```
[T][ ] {taskName}
```

### Deadline
Deadlines are tasks that have a deadline or a date you have to finish the task by. \
Usage: `deadline [taskName] /by [date]`
```
[D][ ] {taskName} (date)
```

### Event
Events are tasks with start and end dates. \
Usage: `event [taskName] /from [date] /to [date]`
```
[E][ ] {taskName} (from {date} to {date})
```

### Dates
Dates can only be read if written in either of the correct formats. Otherwise, the current date will be initialized. \
Date formats:
```
dd/mm/yyyy hhmm
dd-mm-yyyy hhmm
```
here `hhmm` are in military time format.

  ## Deleting
Deletes tasks from the list. Supports deleting more than 1 task at once\
Usage: `delete/del/d [index]...`
```
[task]
 has been ejected. (they were the imposter)
[number] tasks remain.
```

 ## List
Lists current tasks. \
Usage: `list/l`

 ## Find
Finds tasks with matching keyword. Supports wildcards, just use asterisk`*`. \
Usage: `find/f [keyword]` \

Examples: 
- `f todo`
- `find CS**`
- `find Assignment`
- `find deadline`
- `f *09/2024`

 ## Mark
Mark multiple tasks as done.
Usage: `mark/m [index]...`

 ## Unmark
Unmarks multiple tasks.
Usage: `unmark/um [index]...`

 ## Greeting Options
BabyGronk says hi to you! Just say `hi/hello` to him first!

 ## Exiting
When you're finally done with managing your tasks, simply say `bye/exit` and the program closes.
Tasks will be saved into a file in the directory `[root]/data`