# Collection

```java

```

- Collections framework provides a number of libraries, including classes and interfaces, that can help simplify working with data.
- Arrays have a fixed size and cannot be resized. When creating an array, you need to specify its size, and if you want to add or remove items, you'll need to create a new array.
- Collections, on the other hand, offer the benefit of flexibility in size. You can add and remove elements from a collection as needed, and it will automatically grow or shrink to accommodate the changes.
- Collections provide a range of useful methods, such as reversing the order of elements, that can make it easier to manipulate the collection without needing to know the underlying implementation details.
- Java also provides tools for converting between arrays and collections, which can be helpful when working with data in different formats.

# List
- Objects in set does not have guaranteed order.
- List has order.
- Objects are in the list are guaranteed to stay in order you put them in.
- List can contain duplicate.
- Set does not allow duplicate.
- You can't pass in primitive type as object in collection.
- Almost 100% of time you use `ArrayList` class to implement `List` interface type.
- `ArrayList` class uses an array for underlying data structure.
- When you create `ArrayList`, it actually go and create an array to pull all of objects in.
- Another implementation is `LinkedList`.
- Difference between `ArrayList` and `LinkedList` is how it is built and implemented behind the scene.
- There are certain operations that could be slower or faster between `ArrayList` and `LinkedList`, functionality is exactly the same.

## Inserting object at specific index
- You can't insert at some position that doesn't exist yet.

```java
// add object at index 1
names.add(1, "A");
```

## Retrieve value at certain index in the list

```java
// get element at index 2
names.get(2);
```

## Remove element from list

### Remove based on index

```java
// remove element at index 1
names.remove(1);
```

### Remove based on element value
- If we don't know what postion the value is we want to remove.
- It will only remove the first element that matches what you pass in to remove method.

```java
// remove element with value "A"
names.remove("A");
```

## Replace element for something else


## Get length of list
- Array use `.length()` method to get length.
- List use `.size()` method to get length.

## Check if list contain specific element
- If you want to do it with array, you have to loop through elements in the array.
- With list, `.contains()` method checks if the list contains the element.
```java
// check if the list contains element with value "B"
names.contains("B");
```

## Check if list is empty

```java
names.isEmpty();
```

## Clear out list
```java
names.clear();
```

## Sort list with `Collections`

```java
// Sort list in the alphabetical order
Collections.sort(names);
```

## Shuffle list with `Collections`

```java
// Randomly shuffle list in other order
Collections.shuffle(names);
```

## Reverse list with `Collections`

```java
Collections.reverse(names);
```

## Create array using values from list
- You need to pass in `new type[0]` explicitly for the data type of created array.

```java
// create array from list
String[] namesArray = names.toArray(new String[0]);
```

## Create `List` from array
- We need to use `.asList()` method inside `Arrays` class.
- However, `.asList()` method returns fixed size `List` which we cannot add or remove element.
```java
String[] alphabets = {"A", "B", "C", "D"};
List<String> alphabetsList = Arrays.asList(alphabets);
```

### Modifiable `List` from array
- If you want to create freely modify element in the list, you have to do it in a different way.

```java
String[] alphabets = {"A", "B", "C", "D"};
List<String> alphabetsList = new ArrayList<>(Arrays.asList(alphabets));
```



# Set

# Map

# Stack
- Stack class in collection

# Queue
- Queue interface in collection

# Priority Queue (Heap)

