## Reflection 1: Coding Standards

### _1. Meaningful names_
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

### _2. Functions_
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

### _3. Comments_
Comments are well spread across the source code for the more complex logic from the code that I made -- comments aren't included in simple and straight forward code logic to reduce clutter.

### _4. Objects and Data Structures_
Code only implements `List` and `ArrayList` for its data structure.

**e.g.**
```
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();
    ...
```

### _5. Error Handling_
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