/*******************************************************************************
 * Copyright 2011, 2012 Chris Banes.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package uk.co.senab.bitmapcache.samples;

import java.util.ArrayList;

import uk.co.senab.bitmapcache.BitmapLruCache;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView.ScaleType;

public class PugAdapter extends PagerAdapter {

	private final ArrayList<String> mPugUrls;
	private final Context mContext;
	private final BitmapLruCache mCache;

	public PugAdapter(Context context, ArrayList<String> pugUrls) {
		mPugUrls = pugUrls;
		mContext = context;
		mCache = SampleApplication.getApplication(context).getBitmapCache();
	}

	@Override
	public int getCount() {
		return null != mPugUrls ? mPugUrls.size() : 0;
	}

	@Override
	public View instantiateItem(ViewGroup container, int position) {
		NetworkedCacheableImageView imageView = new NetworkedCacheableImageView(mContext, mCache);

		String pugUrl = mPugUrls.get(position);
		imageView.loadImage(mCache, pugUrl);

		imageView.setScaleType(ScaleType.FIT_CENTER);
		container.addView(imageView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

		return imageView;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView((View) object);
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == object;
	}

}
