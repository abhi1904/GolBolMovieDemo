/**
 * Copyright 2016 Erik Jhordan Rey.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.erosnow;

import com.erosnow.services.model.Movie;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import data.FakeRandomUserGeneratorAPI;

import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class BasicMockitoTest {

    private Movie movie;

    @Before
    public void setUpDetailViewModelTest() {
        movie = FakeRandomUserGeneratorAPI.getMovie();
    }

    @Test
    public void shouldgetBackdroppath() {
        assertNotNull(movie.getBackdrop_path());
    }

    @Test
    public void shouldgetId() {
        assertNotNull(movie.getId());
    }

    @Test
    public void shouldgetOriginal_language() {
        assertNotNull(movie.getOriginal_language());
    }

    @Test
    public void shouldgetOriginal_title() {
        assertNotNull(movie.getOriginal_title());
    }

    @Test
    public void shouldgetOverview() {
        assertNotNull(movie.getOverview());
    }

    @Test
    public void shouldgetPopularity() {
        assertNotNull(movie.getPopularity());
    }

    @Test
    public void shouldgetPoster_path() {
        assertNotNull(movie.getPoster_path());
    }

    @Test
    public void shouldgetRelease_date() {
        assertNotNull(movie.getRelease_date());
    }

    @Test
    public void shouldgetTitle() {
        assertNotNull(movie.getTitle());
    }

    @Test
    public void shouldgetVote_average() {
        assertNotNull(movie.getVote_average());
    }

    @Test
    public void shouldgetVote_count() {
        assertNotNull(movie.getVote_count());
    }


}
