# Module 1

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

---

---

# Module 2

## Reflection 1: Code quality issues fixing

* For reaching 100% code coverage, I used JaCoCo to analyze which methods had unhandled test cases. When a method is known
to not have been handled, I usually ask a friend to see what cases I haven't made unit tests for. After that, it's back to
the usual setup-execute-verify-teardown flow for that certain case, then it's rinse and repeat until I achieve 100% code
coverage.
    - Made unit tests for a lot of packages, like `ProductServiceImpl`, `MainController`, `ProductController`, and so on.
    - Covered missed branches, especially inside loops and if statements for `ProductService` and `ProductRepository`.
* For code quality issues, PMD is my trusty tool to scan the codebase. It's connected to my GitHub via Actions, so I can get
a detailed overview of certain errors and warnings that were reported by PMD after every workflow run. I then immediately go
to fixing said violations until it has minimal amounts of warnings and can run the app just fine.
    - Made EshopApplication.java a static class by creating a private constructor to prevent instantiation
    - Removed redundant 'public' modifiers for some interfaces in ProductService.java
    - Renamed some methods in HomePageFunctionalTest.java to follow Java naming conventions
    - Removed unused and/or implicit imports and replaced with necessary imports only

---

## Reflection 2: CI/CD Implementation

I personally think that my project has already followed the definition of CI/CD. I made workflows that can be run every
push or pull request to check for code quality issues inside my codebase. Then, I can go to my Actions page at my GitHub
to evaluate those issues and go fix them. I also made my project have an auto-deploy feature by integration with Koyeb.
This ensures that every change that is published in the `main` branch to be deployed automatically, hassle-free.

---

---

# Module 3

## Reflection 1: Applied Principals

In this project, many of the SOLID principles were already followed, but I specifically focused on improving three key principles:

- *Single Responsibility Principle (SRP)*: I separated `ProductController` from `CarController` to ensure that each controller only handles one specific entity. This prevents a single class from managing multiple responsibilities, making the code more modular, readable, and easier to maintain.

- *Interface Segregation Principle (ISP)*: I introduced a dedicated `CarService` interface instead of directly relying on an implementation class. This ensures that each service interface only contains methods relevant to its specific functionality, preventing unrelated dependencies and making it easier to modify or extend services in the future.

- *Dependency Inversion Principle (DIP)*: I refactored `CarController` to depend on the `CarService` interface rather than `CarServiceImpl`, promoting loose coupling. Additionally, I applied constructor injection instead of field injection, which eliminates hidden dependencies and makes the code more testable and flexible for future changes.

---

## Reflection 2: SOLID Principles Advantages

By applying SOLID principles, my project is now more modular, maintainable, and scalable. Separating `ProductController` from `CarController` (SRP) ensures each class has a clear, single responsibility, making modifications easier. Creating a dedicated `CarService` interface (ISP) prevents unnecessary dependencies and improves flexibility. Using DIP by making `CarController` depend on `CarService` instead of `CarServiceImpl` reduces tight coupling and enhances testability. These improvements make my code cleaner, easier to test, and more adaptable for future changes.

---

## Reflection 3: SOLID Principles Disadvantages

While SOLID principles improve maintainability and scalability, they can also introduce complexity and boilerplate code, making the project more verbose. Excessive abstraction may lead to performance overhead and can make it harder for new developers to understand the code. Additionally, refactoring an existing project to follow SOLID can be time-consuming.

---

---

# Module 4

## Reflection 1: TDD Flow

Menurut saya, penerapan Test-Driven Development (TDD) akan sangat bermanfaat jika dilakukan sejak awal pengembangan.
Dengan pendekatan ini, saya dapat merancang berbagai skenario input pengguna terlebih dahulu sebelum menentukan implementasi
yang paling sesuai. TDD juga membantu saya mengevaluasi apakah kode yang saya buat sudah benar, mudah dipelihara, dan
mengikuti alur kerja yang produktif. Namun, di sisi lain, saya masih perlu beradaptasi karena belum terbiasa menulis test
terlebih dahulu sebelum mengembangkan fitur.

---

## Reflection 2: F.I.R.S.T. Principle Usage

Untuk aspek F (FAST), saya belum bisa memastikan kecepatan eksekusi karena jumlah test yang saya miliki masih sedikit,
dan saya belum dapat membandingkannya dengan ribuan unit test. Untuk aspek I (Isolated), saya yakin bahwa test yang saya
buat sudah memenuhi prinsip ini, karena setiap test bersifat independen dan tidak bergantung satu sama lain. Untuk aspek
R (Repeatable), test yang saya jalankan tidak bergantung pada lingkungan tertentu dan memiliki data yang terisolasi,
sehingga sudah sesuai dengan prinsip ini. Untuk aspek S (Self-validating), seluruh test telah terautomasi dan dapat
dijalankan langsung melalui IntelliJ tanpa memerlukan pengecekan manual. Sementara itu, untuk aspek T (Thorough/Timely),
masih ada beberapa kasus yang belum tertangani sepenuhnya. Saya mengakui bahwa ada beberapa test case yang saya hapus
karena mengalami error yang sulit dijelaskan. Namun, ke depannya, jika saya memiliki lebih banyak waktu, saya akan
memastikan semua test dapat berjalan dengan baik agar seluruh kasus dapat diuji secara menyeluruh.

---

---