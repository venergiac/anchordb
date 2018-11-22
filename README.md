# Anchordb

Asset and Tag Registry for IoT

 
![logo](https://github.com/venergiac/anchordb/blob/master/logo2.png "Logo")


## Description

AnchorDb is a flexible Asset registry for IoT; it supportsng tag and asset.

![schema1](https://github.com/venergiac/anchordb/blob/master/schema1.png "Schema 1")


### Asset Class

An asset class is special node of the Asset Network which define the Asset types available on the system.

Asset Class (aka: class):
* includes Id, Name, Creation Timestamp and Validiy
* includes 0:N Attributes

Attributes of Asset calss can be empty.

### Asset Instance

An asset instance is the key object. Aset instance reptresents the real asset (turbine, compressor, pump, valve, system).
Asset instances (aka: asset):
* includes Id, Name, Creation Timestamp and Validiy
* is an (1:1) instance of Asset Class
* includes 0:N Attributes
* includes 0:N Tags
* is part of a (0:1) hierarchy of assets
* can be optionally (0:N) related to ather assets

### Relations

Relation is a labeled bidirectional relationship among assets. 

A special mandatory relation is a hierarchycal relations to Asset Instance Parent in the hierarchy.


eg. 

* Plant
   * Wind Turbine


Relations supports historical versioning.

Relation:
* includes Id, Name, Creation Timestamp and Validiy
* includes start and end nodes

Historical relation:
* includes Id
* array of 
  * Name, Creation Timestamp, Version and Validiy
  * start and end nodes

### Attributes

Attributes can normal, historical and shared.

Normal attribute:
* include Id, Name, Type, Nullable, Value
* belongs Asset Instance

Historical attribute:
* include Id, Name, Type, Value
* include array of Values
* belongs Asset Instance


Shared attribute:
* include Id, Name, Type, Value
* belongs Asset Instance

shared attributes are automatically inherited by Asset Instance childs of the Parent Asset Instance.

The Type of attributes can be:
* String
* Numeric
* JSON
* Array
* Shadow: link to another shared attribute; in this case the value is the same of the shared attributes

### Tag Class

Tag Class is a specific Asset Class with name "Tag"

Tag Class (aka: tag class):
* includes Id, Name, Creation Timestamp and Validiy
* includes 0:N Attributes
* includes 1 String attribute called UoM
* includes 1 String attribute called Alias

### Tag

Tag is an instance of Tag Class

Tag  (aka: tag):
* includes Id, Name, Creation Timestamp and Validiy
* includes 0:N Attributes
* includes 1 String attribute called UoM
* includes 1 String attribute called Alias
* can be optionally (0:N) related to ather Tag


## APIs
TBD


## Architecture

AnchorDb has two packages
* Library with SDK to intercat with Databases
* REST API Microservice

### Supported Db

AnchorDb is based 

* on Neo4J 
* in Memory (tbd)
* REDIS (tbd)
* SQL (tbd)
* S3 (tbd)


## Installation

TBD

