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

package uk.ac.hutton.ics.knodel.service;

import android.content.*;
import android.widget.*;

/**
 * The {@link RestletCallback} is a very basic callback with an {@link #onSuccess(T)} and a {@link #onFailure(Exception)} method.
 *
 * @param <T> The type of the server result
 * @author Sebastian Raubach
 */
public abstract class RestletCallback<T>
{
	private Context context;

	public RestletCallback(Context context)
	{
		this.context = context;
	}

	/**
	 * Called when the Restlet request succeeds.
	 *
	 * @param result The result returned by the server.
	 */
	public abstract void onSuccess(T result);

	/**
	 * Called when the Restlet request fails.
	 *
	 * @param caught The {@link Exception} returned from the server
	 */
	public void onFailure(Exception caught)
	{
		caught.printStackTrace();

		// TODO: error handling
		Toast.makeText(context, "ERROR: " + caught.getLocalizedMessage(), Toast.LENGTH_LONG).show();
	}
}
