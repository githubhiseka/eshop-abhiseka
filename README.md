## Reflection 1: Coding Standards

### _Meaningful names_
Variables, methods, and classes follow naming conventions while also keeping it short, easily understandable, and memorable.

**e.g.**
```
@PostMapping("/edit")
public String editProductPost(@Valid @ModelAttribute Product product, BindingResult result, Model model) {
    // same as create product workflow
    if (result.hasErrors()) {
        return "editProduct";
    }

    service.update(product);
    return "redirect:list";
```
* `editProductPost` easily translates to the POST method for the 'Edit product' feature.
* Even though it is a `BindingResult` object, the name of the argument could be shortened to just `result` to prioritize readability.

### _Functions_
Every function does one thing and one thing only to keep it small and easily separated from other functions

**e.g.**
```
@GetMapping("/delete/{productId}")
public String deleteProduct(@PathVariable String productId) {
    service.delete(productId);
    return "redirect:/product/list";
    }
```
`deleteProduct` deletes the product based on the ID and returns back to the product list page as a final action.

### _Comments_
Comments are well spread across the source code for the more complex logic from the code that I made -- comments aren't included in simple and straight forward code logic to reduce clutter.

### _Objects and Data Structures_
Code only implements `List` and `ArrayList` for its data structure.

**e.g.**
```
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();
    ...
```

### _Error Handling_
Code still has methods that returns null and there are no utilization of exceptions.

**e.g.**
```
@Override
public Product findById(String productId) {
    Iterator<Product> productIterator = productRepository.findAll();
    // iterates and find matching ID
    while (productIterator.hasNext()) {
        Product product = productIterator.next();
        if (product.getProductId().equals(productId)) {
            return product;
        }
    }
    return null; // return null if the product is not found
```

---

## Reflection 2: Testing

### _1. Unit test evaluation_
Writing unit tests felt proper and systematic because it does eliminate some confusion about data manipulation things.
I don't think there is a certain specific number of tests that should be made in a class because situations can vary a
lot and a lot of edge cases could be present. To make sure unit tests are sufficient for a program, it could be beneficial
to use code coverage analysis, test key scenarios, and consider them as evaluation. 100% code coverage doesn't mean a
program is bug-free because code coverage only shows execution and doesn't really test complex and logical cases for
real-time scenarios.