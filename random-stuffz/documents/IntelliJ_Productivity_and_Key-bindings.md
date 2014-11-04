#Productivity, Key-bindings and other awesomeness for IntelliJ IDEA
###by Simen Bekkhus
######Very much a work in progress - Please send me a message, or better yet, a Pull Request, if you know of more!

##Table of Contents
* [Useful stuff to know](#useful-stuff-to-know)
* [Key-bindings](#key-bindings)
* [Tips & Tricks](#tips--tricks)
* [TODOs](#todos)

##Useful stuff to know
* [Homepage][1]
* IntelliJ is a multi-platform, polyglot (supports many languages) IDE from [JetBrains][2], the makers of [ReSharper][3], a C# and .NET plugin for Visual Studio.
 * IntelliJ is mainly centred on Java and Web-development, with support for many languages, including [Java][4], [JavaScript][5], Python, [Ruby][6], [PHP][7], [SQL][8] and many more
 * IntelliJ also bundles support for many frameworks and technologies, such as [Android][9], [Spring][10], [Hibernate][11], [Play][12], [NodeJS][13] and others.
 * There is also integration including for other services, such as [VCS][14] (Git, Subversion etc.), [build tools][15] (Maven, Gradle, Ant), [Databases][8] (including a terminal), a REST-service, [Servers][16] (GlassFish, WebLogic, TomCat) and many more.
* IntelliJ is being developed as a (open source) platform, which means it can be extended and split up. There are multiple smaller IDEs based on IntelliJ (such as [Android Studio][17], [PhpStorm][18], [WebStorm][19], [RubyMine][20] etc.), as well as multiple plugins (such as [AngularJS][21], [C/C++ support][22], [static code analysis][23], [JRebel][24], [Atlassian Tools][25] and many more).
* See [here][26] for a full feature-list.

##Key-bindings
######This is the fun part!
3 quick points before the awesomeness commences:

1. I'd HIGHLY recommend installing a plugin for IntelliJ called ["Key Promoter"][27]. This plugin will tell you if you do something using your mouse (which is slow, thus bad), that you could have done with your keyboard, where your fingers already were (which is quick, thus awesome). In addition, if you use a command that does _not_ already have a key binding, it will prompt you to create one.
2. Mac has 2 different keymaps, see [Tips & Tricks](#tips--tricks) for why that is.
3. `ctrl shift a` is your best friend in the world. It allows you to search through all menu options and commands, showing you the key-binding, as well as letting you run the command. Its usefulness is ridiculous once you get used to it.

Now then, let’s bounce!

PS: I'll use capitalized characters (e.g. 'L' instead of 'l') to make it more obvious what char is used.  
PPS: The use of parentheses denotes an alternative, or variation, of a shortcut, which does something more, and related to the "normal" usage.  
PPPS: This list will be horribly disorganised. I'll try to make it more clean in the future.
***
* Basic Auto-complete: `ctrl space`
 * This is the basic auto-complete we all know and love. If you press `space` again while holding in `ctrl`, it'll show all suggestions, also stuff not yet imported. Lastly, it will suggest name of variables when declaring them.
* Smart Auto-complete: `ctrl shift space`
 * This is the same as the normal variant, but it's type-aware. Meaning that if you know it's a String you need, it'll just suggest Strings.
* Find/Replace: `ctrl (shift) F/R`
 * Pretty standard. Adding `shift` allows you to search all files, with a variety of filters, instead of only the current file.
* Duplicate line: `ctrl D`
 * Duplicates the current line.
* Delete line: `ctrl Y`
 * Delete the current line.
* Generate: `alt insert`
 * This will open the 'generate' window. Depending on where your selection is, it'll be different windows. E.g. While in a class, it'll ask if you mean getters & setters, constructors etc.. If you're in the project explorer, it'll suggest files depending on the type of folder, such as classes, Android layouts, SQL-files etc..
* Run: `(ctrl) (alt) shift f10`
 * This will run the current configuration. If you hold `ctrl` as well, it'll run the current context (e.g. the test-class you're in). If you hold `alt`, you'll get a list of run configurations to choose from
* Debug: `(alt) shift f9`
 * Will debug the current configuration. Adding `alt` will allow you to choose between run-configurations.
* Quick fix: `alt enter`
 * Proposes a quick fix to a potential problem, or just a refactoring. E.g. inverting an assertion, converting a for-loop to a foreach-loop or fixing an import.
* Rename...: `shift f6`
 * Rename a variable, method, class, file etc..
* (Un)comment: `ctrl (shift) /`
 * Comments out, or in if already commented the selection. Adding `shift`
* Format code: `ctrl alt L`
 * Auto-formats the code. This can be configured to also organise imports and perform code cleanup as well.
* Optimise imports: `ctrl alt O`
* Show usages: `alt f7`
 * Searches for where a symbol is used elsewhere in the codebase.
* Multiple selections:
 * `alt shift *mouseclick*` place additional cursor
 * `alt (shift) J` (de)select next occurrence of word
 * `alt shift ctrl J` Select all occurrences of word in file
 * `alt shift *mouseclick*` place additional cursor
 * `alt *drag mouse*` make a vertical selection
* Live templates: `ctrl (alt) J`
 * This will suggest live-templates. A live template means e.g. writing `sout`, pressing `tab` and getting `System.out.println();`. This shortcut will give you a list of the ones available. Adding `alt` to the mix will try to _surround with_ a live template.
* Extract variable: `ctrl alt V`
 * Extracts your selection, and puts in in a variable. This can also be used to avoid having to write the lhs (declaration) of a variable.
* Extract method `ctrl alt M`
 * Same as above, only for a method.
* Extract method `ctrl alt F`
 * Same as above, only for a field.
* Extract method `ctrl alt C`
 * Same as above, only for a constant.
* Open class, file or symbol: `ctrl (shift (alt)) N`
 * Allows you to search for a class. Adding `shift` will search for any file, not just class. Adding a `alt` as well will search for symbol, meaning a field or a method. Pressing the command again will search in all available files (i.e. libraries), not just your own.
* Find symbol in current file: `ctrl f12`
 * Finds members in current class, meaning a field or a method.
* (De)select word: `ctrl (shift) W`
 * Incrementally selects a word. Goes from word to parameter, to method etc.. Adding `shift` goes the other way.
* Surround with: `ctrl alt T`
 * Surrounds the current selection with a variety of options. Typically an assertion or a loop.
* Paste simple: `ctrl alt shift V`
 * Do a simple (normal) paste. The default paste (`ctrl V`) will format your text, as well as escaping as needed. Using this command avoids that.
* Paste from history: `ctrl shift V`
 * Allows you to paste from history. The default stack-size is 5, this can be increased.
* Go to definition: `ctrl B` or `ctrl click`
 * Go to the definition of a symbol.
* Quick 'Doc'umentation: `ctrl Q`
 * Open the JavaDoc or other documentation in a popup window, instead of navigating to it.
* Quick definition: `ctrl shift I`
 * Same as above, only the implementation instead.
* Override methods: `ctrl O`
 * Get a list of methods available to override
* Implement methods: `ctrl I`
 * Same as above, only for interfaces
* Parameter info: `ctrl P`
 * Get info about the parameters. Will give a small popup saying which parameters the method receives.
* Switcher: `ctrl tab`
 * Gives a list showing all that is open, allowing you to quickly shift between them. Jumps to the selection when `ctrl` is released.
* Open recent files: `ctrl (shift) E`
 * Allows you to open recently opened files. Adding `shift` lets you scroll through only files you've edited, not just opened.
* Cycle recent changes: `ctrl backspace`
 * Cycles through your recent changes in files, in a reversed, chronological order. Jumps between files, both open ones, and the ones you've closed.
* Evaluate expression: `alt f8`
 * During debug, this allows you to run the code and figure out what it evaluates to.
* Go to test: `ctrl shift T`
 * Navigates to the associated test. If none is applicable, it prompts for the creation of the test-class.
* Update project: `ctrl T`
 * Make a pull using VCS
* See changes: `alt 9`
 * See a list of changed files (integration with VCS, otherwise local file history), as well as a commit log

##Tips & Tricks
* In every popup and menu, you can filtrate the content just by typing. From options to generate, this allows you to quickly find what you're looking for in a window.
* All searching also supports camelHumping and wildcards to filtrate. E.g. will `ABS` suggest `ActionBarSherlock` before a class named `Absolutely`.
* Since IntelliJ is a polyglot IDE, it also supports language injection. This means that you can write e.g. SQL inside of a Java-class, and get autocomplete and Syntax-highlighting.
* Keymaps
 * The keymap is available from within IntelliJ. Go to Help -> Default Keymap Reference to open it.
 * Windows and Linux share the same keymap, while OSX has the option of 2 different. One aimed towards those also using different platforms (thus very much alike Windows/Linux), and one aimed towards those sitting exclusively/mostly on OSX, with key-bindings more closely resembling those found in other programs on the platform.
* IntelliJ natively supports multiple monitors.
 * Just drag and drop a tab or tool/window, and it'll just work.
* IntelliJ supports vertical selection using the mouse. Simply hold `alt` down while selecting. This enables multiple cursors.
* Using `shift alt up/down` allows you to move blocks of codes up and down. It's context-sensitive, meaning it'll move a method above another if the whole thing is selected, not into it.
* Using `ctrl /` (comment) when having no selection, automatically comments out the current line, and jumps to the next one. This allows you to quickly comment out multiple consecutive lines.
 * Similarly, using `ctrl C/X` with no selection copies/cuts the whole current line.
* You can compare a section with the text in your clipboard
* Writing `/**` and pressing enter will generate quick documentation for you, with all parameters and return-values.
* When using an auto-complete list, there are four different alternatives for completing the word.
 1. Using `enter`. This completes the current word.
 2. Using `tab`. This completes the current word, and deletes the stuff after it, up to a `.` or `()`.
 3. Using `.`. This automatically puts a `.` after the word, allowing you to quickly chain.
 4. Using `!`. This automatically puts a `!` in front of the word, thus negating it.
* If you use Visual Studio and .NET, you've probably heard about ReSharper. Because it's made by the same people that IntelliJ, you can get the same shortcuts for Visual Studio as the ones in IntelliJ.
 * To enable it, go to the ReSharper Options in Visual Studio. Under 'Keyboard & Menus' you'll be able to select 'ReSharper 2.x or IntelliJ IDEA'. When you now use a shortcut for something that has a IntelliJ equivalent, it'll prompt you to choose which implementation to use.
 * If you go the other way (From ReSharper in Visual Studio to IntelliJ), you can bring your shortcuts that way as well. Se [this blog-post][28] for more information.
* A cool blog-series on different features in IntelliJ is available [here][29].

##TODOs
######Stuff not implemented yet. I'm sorry! D-:
* Clean up the list of shortcuts
* Find a clever way to denote variations of a shortcut.
* Stuff other than key-bindings.
* Links to videos, resources(?).
* More tips&tricks.

## Bonus

[tl;dr][30]

[1]:http://www.jetbrains.com/idea/
[2]:http://www.jetbrains.com/
[3]:http://www.jetbrains.com/resharper/
[4]:http://www.jetbrains.com/idea/features/code_editor.html
[5]:http://www.jetbrains.com/editors/javascript_editor.jsp
[6]:http://www.jetbrains.com/idea/features/ruby_rails.html
[7]:http://www.jetbrains.com/idea/features/php_editor.html
[8]:http://www.jetbrains.com/idea/features/database_tools.html
[9]:http://www.jetbrains.com/idea/features/android.html
[10]:http://www.jetbrains.com/idea/features/spring_framework.html
[11]:http://www.jetbrains.com/idea/features/jpa_hibernate.html
[12]:http://www.jetbrains.com/idea/features/play_framework.html
[13]:http://www.jetbrains.com/idea/features/nodejs.html
[14]:http://www.jetbrains.com/idea/features/version_control.html
[15]:http://www.jetbrains.com/idea/features/build_tools.html
[16]:http://www.jetbrains.com/idea/features/java_ee.html
[17]:http://developer.android.com/sdk/installing/studio.html
[18]:http://www.jetbrains.com/phpstorm/
[19]:http://www.jetbrains.com/webstorm/
[20]:http://www.jetbrains.com/ruby/
[21]:http://plugins.jetbrains.com/plugin/6971
[22]:http://plugins.jetbrains.com/plugin/1373
[23]:http://plugins.jetbrains.com/plugin/4594
[24]:http://plugins.jetbrains.com/plugin/4441
[25]:http://plugins.jetbrains.com/plugin/2190
[26]:http://www.jetbrains.com/idea/features/index.html
[27]:http://plugins.jetbrains.com/plugin/4455
[28]:http://hadihariri.com/2012/02/17/the-kotlin-journey-part-i-getting-things-set-up/
[29]:http://grahamhackingscala.blogspot.no/2010_08_01_archive.html
[30]: http://www.jetbrains.com/idea/docs/IntelliJIDEA_ReferenceCard.pdf "Official IDEA reference card"
