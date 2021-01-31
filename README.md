## Spring-Rest-Tutorial
Yeah, I did the Spring [long-form rest service tutorial](https://spring.io/guides/tutorials/rest/).
Not to brag or anything, but I still even understood most of it.

I'm using this to brush up on my Java and Spring skills, which have not been getting much use.
I'd highly recommend checking it out in the link above.

### Details
We have the typical business logic example of an Employee datastore and MVC here.

#### Employee
Simple JPA Entity with tagged up fields for autowiring later

#### EmployeeController
This isolates the transactional API logic. Here we define the routes, how we handle requests,
and how we build responses.

#### EmployeeModelAssembler
Now this is an interesting one. 
This class has the function of taking our Employee Entity objects
and elaborating on their semantics by putting hyperlinks to related RESTful resources.

It does this by using the Spring HATEOAS framework's WebMvcLinkBuilder methods to determine
the links:
- We pass it the Controller class methods (routes) used for the Entity in question (in this case, Employee)
- We use the appropriate link builders to tell Spring what kind of link we're building
  - i.e. with withRel(String) or withSelfRel()

We use the EmployeeModelAssembler in the Controller to construct robust RESTful resource models

#### EmployeeNotFoundAdvice
This is a kind of interceptor for the custom Exception we defined.
When the custom Exception gets thrown, Spring will use this handler 
to define how the application responds.
- The Exception: EmployeeNotFoundException

#### PreLoadDataBase
Necessary CommandLineRunner Bean. We are using an H2 (in-memory) database, so there's no actual data at the start.
Spring scans for CommandLineRunner Beans, and runs them after *CONTEXT* is loaded. 

[It's all about the Context](https://www.youtube.com/c/scholagladiatoria)
