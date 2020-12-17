package com.google.mlkit.mlkitcommon.c;


import org.moe.natj.c.CRuntime;
import org.moe.natj.c.ann.CVariable;
import org.moe.natj.general.NatJ;
import org.moe.natj.general.ann.Generated;
import org.moe.natj.general.ann.Library;
import org.moe.natj.general.ann.MappedReturn;
import org.moe.natj.general.ann.Runtime;
import org.moe.natj.objc.map.ObjCStringMapper;

@Generated
@Library("MLKitCommon")
@Runtime(CRuntime.class)
public final class MLKitCommon {
	static {
		NatJ.register();
	}

	@Generated
	private MLKitCommon() {
	}

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String MLKModelDownloadDidSucceedNotification();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String MLKModelDownloadDidFailNotification();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String MLKModelDownloadUserInfoKeyRemoteModel();

	@Generated
	@CVariable()
	@MappedReturn(ObjCStringMapper.class)
	public static native String MLKModelDownloadUserInfoKeyError();
}