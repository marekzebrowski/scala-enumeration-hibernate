scala-enumeration-hibernate
===========================

Support for scala Enumeration and case objects in Hibernate

So you have Scala project that uses hibernate and you need to persist Enumeration... 
Sadly Hibernate does not support it out of the box.

Fortunately it is not so hard to implement a UserType that can fix the problem

If you preffer case objects way, there is much more work to be done, as you need to make a UserType 

How to use included code:
-------------------------

1. copy EnumerationAbstractUserType to your project
2. for each Enumeration create a class that extends EnumerationAbstractUserType (example MyOptUserType)
3. define mapping in hbm.xml or using annotations 
4. If you need to persist Enumerations as integers use EnumerationIntAbstractUserType

For persisting case objects see OptCaseUserType class
It is only a template how to write your own. Probably it can be generalized.

It is not a full-scale hibernate support library, only a bunch of code for solving one particular problem

