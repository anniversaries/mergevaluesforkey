# mergevaluesforkey
> A utility to merge different values for entries of a same key to a single entry

[greek-namedays](https://github.com/anniversaries/greek-namedays) uses a raw file to parse the name days data. This file contains duplicate keys (because it is just an extract from a database).
Example:
```
"18/01": "Αθανάσιος",
...
"18/01": "Νικόλαος",
```

The desired behavior is to definitely have a single key with all the possible key values as a value (list of Strings):

```
...
"18/01": "Αθανάσιος, Νικόλαος",
...
```

`mergevaluesforkey` comes to the rescue.