/*
 * Copyright (c) 2016 Information & Computational Sciences, The James Hutton Institute
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.ac.hutton.ics.knodel.database;

import android.content.*;
import android.database.*;
import android.database.sqlite.*;

import java.io.*;

/**
 * {@link DatabaseInternal} extends {@link SQLiteOpenHelper} and contains convenience methods for handling local sqlite files.
 *
 * @author Sebastian Raubach
 */
public class DatabaseInternal extends SQLiteOpenHelper
{
	private static final String DATABASE_NAME    = "knodel";
	private static final int    DATABASE_VERSION = 1;

	private Context context;
	private int     datasourceId;

	public DatabaseInternal(Context context, int datasourceId)
	{
		super(context, DATABASE_NAME + datasourceId, null, DATABASE_VERSION);
		this.context = context;
		this.datasourceId = datasourceId;
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
	}

	public SQLiteDatabase openFromFile()
	{
		return SQLiteDatabase.openOrCreateDatabase(new File(new File(new File(context.getFilesDir(), "data"), Integer.toString(datasourceId)), datasourceId + ".sqlite"), null);
	}

	/**
	 * The {@link AdvancedCursor} wraps a {@link Cursor} and adds methods to get values based on column name rather than column index.
	 *
	 * @author Sebastian Raubach
	 */
	public static class AdvancedCursor
	{
		private Cursor cursor;

		public AdvancedCursor(Cursor cursor)
		{
			this.cursor = cursor;
		}

		public String getString(String columnName)
		{
			return cursor.getString(cursor.getColumnIndex(columnName));
		}

		public int getInt(String columnName)
		{
			return cursor.getInt(cursor.getColumnIndex(columnName));
		}

		public long getLong(String columnName)
		{
			return cursor.getLong(cursor.getColumnIndex(columnName));
		}

		public double getDouble(String columnName)
		{
			return cursor.getDouble(cursor.getColumnIndex(columnName));
		}

		public float getFloat(String columnName)
		{
			return cursor.getFloat(cursor.getColumnIndex(columnName));
		}

		public boolean getBoolean(String columnName)
		{
			return cursor.getInt(cursor.getColumnIndex(columnName)) == 1;
		}
	}
}
