/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.psato.devfest.di.module;

import com.psato.devfest.data.remote.TraktAPI;
import com.psato.devfest.data.repository.show.ShowRepository;
import com.psato.devfest.data.repository.show.ShowRepositoryImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class ShowRepositoryModule {

    @Provides
    ShowRepository provideUserRepository(TraktAPI traktAPI) {
        return new ShowRepositoryImpl(traktAPI);
    }
}
