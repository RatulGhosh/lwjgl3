/*
 * Copyright LWJGL. All rights reserved.
 * License terms: http://lwjgl.org/license.php
 */
package org.lwjgl.system.macosx;

import org.lwjgl.system.DynamicLinkLibrary;

import static org.lwjgl.system.MemoryUtil.*;

/** Implements a {@link org.lwjgl.system.DynamicLinkLibrary} on the MacOS X. */
public abstract class MacOSXLibrary extends DynamicLinkLibrary.Default {

	private final String name;

	protected MacOSXLibrary(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public long getFunctionAddress(CharSequence name) {
		return getFunctionAddress(memEncodeASCII(name));
	}

	public static MacOSXLibrary create(String name) {
		if ( name.endsWith(".framework") )
			return new MacOSXLibraryBundle(name);
		else
			return new MacOSXLibraryDL(name);
	}

}

