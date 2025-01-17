/*
Copyright (c) 2008 Health Market Science, Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

package com.healthmarketscience.sqlbuilder;


import java.util.List;

/**
 * Query which generates a series of SELECT queries joined by UNION clauses.
 * <p>
 * Note, any of the SetOperationQuery types (UNION [ALL], EXCEPT [ALL],
 * INTERSECT [ALL]) may be used with this Query, despite the name.
 *
 * @author James Ahlborn
 */
public class UnionQuery extends SetOperationQuery<UnionQuery> {
  
  public UnionQuery(Type type) {
    this(type, (SelectQuery[])null);
  }

  public UnionQuery(Type type, SelectQuery... queries) {
    super(type, (Object[])queries);
  }

  public UnionQuery(Type type, List<SqlObject> queries) {
    super(type, queries.toArray());
  }

}
