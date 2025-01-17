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

import com.healthmarketscience.common.util.AppendableExt;

import java.io.IOException;



/**
 * Outputs the given query surrounded by parentheses
 * <code>"(&lt;query&gt;)"</code>, useful for embedding one query within
 * another.
 *
 * @author James Ahlborn
 */
public class Subquery extends Expression
{

  protected SqlObject _query;
  private SqlObject _sqlObject;

  /**
   * {@code Object} -&gt; {@code SqlObject} conversions handled by
   * {@link Converter#toCustomSqlObject(Object)}.
   */
  public Subquery(Object query) {
    _query = ((query != null) ? Converter.toCustomSqlObject(query) :
              null);
  }

  public SqlObject getSqlObject() {
    return _sqlObject;
  }

  public Subquery setAlias(Object sqlObject) {
    this._sqlObject = Converter.toCustomSqlObject(sqlObject);
    return this;
  }

  @Override
  public boolean isEmpty() {
    return _query == null;
  }
  
  @Override
  protected void collectSchemaObjects(ValidationContext vContext) {
    vContext.collectNestedQuerySchemaObjects(_query);
  }
    
  @Override
  public void appendTo(AppendableExt app) throws IOException {
    appendCustomIfNotNull(app, _query);
    if (_sqlObject != null)
      app.append(" ").append(_sqlObject);
  }

}
